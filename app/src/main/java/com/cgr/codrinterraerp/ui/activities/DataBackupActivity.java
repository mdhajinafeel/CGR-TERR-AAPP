package com.cgr.codrinterraerp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.text.format.Formatter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.constants.IAPIConstants;
import com.cgr.codrinterraerp.model.BackupModel;
import com.cgr.codrinterraerp.ui.adapters.RecyclerViewAdapter;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.utils.CommonUtils;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DataBackupActivity extends BaseActivity {

    private RecyclerView rvDataBackupList;
    private List<BackupModel> backupModelList = new ArrayList<>();
    private RecyclerViewAdapter<BackupModel> backupModelRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_backup);
        statusBarSetting();
        hideKeyboard(this);
        initComponents();
    }

    private void initComponents() {
        try {
            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);
            MaterialButton btnCreateBackup = findViewById(R.id.btnCreateBackup);
            rvDataBackupList = findViewById(R.id.rvDataBackupList);

            txtTitle.setText(getString(R.string.data_backup));
            imgBack.setOnClickListener(view -> finish());

            btnCreateBackup.setOnClickListener(view -> generateBackup());
        } catch (Exception e) {
            AppLogger.e(getClass(), "initComponents", e);
        }
    }

    private void generateBackup() {
        try {
            if (checkPermission()) {
                exportDatabase();
            } else {
                requestPermission();
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "generateBackup", e);
        }
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= 30) {
            return Environment.isExternalStorageManager();
        }
        return ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") == 0
                && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + getPackageName()));
                manageAllFilesAccessPermissionLauncher.launch(intent);
            } catch (Exception unused) {
                Intent intent2 = new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION");
                manageAllFilesAccessPermissionLauncher.launch(intent2);
            }
        } else {
            ActivityCompat.requestPermissions(DataBackupActivity.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 101);
        }
    }

    private final ActivityResultLauncher<Intent> manageAllFilesAccessPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (Build.VERSION.SDK_INT >= 30 && Environment.isExternalStorageManager()) {
                            Toast.makeText(getApplicationContext(), getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                        }
                    });

    private void exportDatabase() {
        try {

            if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
                startActivity(new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION"));
            }

            File dbFile = getDatabasePath(IAPIConstants.DBNAME);
            File exportDir = new File(Environment.getExternalStorageDirectory(), "Codrin Group");

            if (!exportDir.exists() && !exportDir.mkdirs()) {
                AppLogger.e(getClass(), "Failed to create directory: " + exportDir.getAbsolutePath());
                return;
            }

            String timeStamp = CommonUtils.convertTimeStampToDate(CommonUtils.getCurrentLocalDateTimeStamp(), "dd_MM_yyyy_HH_mm_ss_S", getApplicationContext());

            File zipFile = new File(exportDir, "cgr_terra_erp_" + timeStamp + ".zip");

            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {

                // 📦 Database files
                addFileToZip(dbFile, zos, "database/" + dbFile.getName());
                addFileToZip(new File(dbFile.getPath() + "-wal"), zos, "database/" + dbFile.getName() + "-wal");
                addFileToZip(new File(dbFile.getPath() + "-shm"), zos, "database/" + dbFile.getName() + "-shm");

                // 📁 Uploads folder
                File uploadsDir = new File(getFilesDir(), "uploads");
                addFolderToZip(uploadsDir, zos);
            }

            Toast.makeText(getApplicationContext(), getString(R.string.database_exported_successfully), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            AppLogger.e(getClass(), "exportDatabaseAsZip", e);
        }
    }

    private void addFileToZip(File file, ZipOutputStream zos, String zipEntryName) {
        if (!file.exists()) return;

        try (FileInputStream fis = new FileInputStream(file)) {

            ZipEntry zipEntry = new ZipEntry(zipEntryName);
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[4096];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
        } catch (Exception e) {
            AppLogger.e(getClass(), "addFileToZip", e);
        }
    }

    private void addFolderToZip(File folder, ZipOutputStream zos) {
        if (folder == null || !folder.exists() || !folder.isDirectory()) return;

        File[] files = folder.listFiles();
        if (files == null) return;

        int index = 1;

        for (File file : files) {
            if (file.isFile()) {
                String newFileName = "upload_" + index + "_" + file.getName();
                addFileToZip(file, zos, "uploads" + "/" + newFileName);
                index++;
            }
        }
    }

    private void loadBackups() {
        try {
            backupModelList.clear();

            File backupDir = new File(Environment.getExternalStorageDirectory(), "Codrin Group");
            if (!backupDir.exists()) {
                return;
            }

            File[] files = backupDir.listFiles();
            if (files == null || files.length == 0) {
                return;
            }

            Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));

            for (File file : files) {
                BackupModel model = new BackupModel();
                model.file = file;
                model.name = file.getName();
                model.size = Formatter.formatShortFileSize(getApplicationContext(), file.length());
                model.date = DateFormat.format("dd MMM yyyy hh:mm a",file.lastModified()).toString();
                backupModelList.add(model);
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "loadBackups", e);
        }
    }
}