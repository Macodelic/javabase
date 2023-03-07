package ru.webapp.basejava.storage;

import ru.webapp.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElem(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void fillDelete(String uuid, int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}
