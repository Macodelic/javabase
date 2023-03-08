package ru.webapp.basejava.storage;

import ru.webapp.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElem(Resume r, int index) {
        int insertIndex = Math.abs(index + 1);
        System.arraycopy(storage, insertIndex,
                storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void fillDeletedElem(String uuid, int index) {
        System.arraycopy(storage, index + 1,
                storage, index, size - index);
    }

}
