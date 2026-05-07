package com.cgr.codrinterraerp.repository;

import androidx.lifecycle.LiveData;

import com.cgr.codrinterraerp.db.dao.ContainerImagesDao;
import com.cgr.codrinterraerp.db.entities.ContainerImages;

import java.util.List;

public class ContainerImagesRepository {

    private final ContainerImagesDao containerImagesDao;

    public ContainerImagesRepository(ContainerImagesDao containerImagesDao) {
        this.containerImagesDao = containerImagesDao;
    }

    public long insert(ContainerImages image) {
        return containerImagesDao.insert(image);
    }

    public LiveData<List<ContainerImages>> getContainerImages(String tempDispatchId) {
        return containerImagesDao.getContainerImages(tempDispatchId);
    }

    public int hardDeleteImage(String id) {
        return containerImagesDao.hardDeleteImage(id);
    }

    public int softDeleteImage(String id) {
        return containerImagesDao.softDeleteImage(id);
    }
}