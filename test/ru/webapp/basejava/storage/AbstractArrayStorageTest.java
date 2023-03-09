package ru.webapp.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.webapp.basejava.exception.ExistStorageException;
import ru.webapp.basejava.exception.NotExistStorageException;
import ru.webapp.basejava.exception.StorageException;
import ru.webapp.basejava.model.Resume;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private final IStorage storage;

    public AbstractArrayStorageTest(IStorage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        new ArrayStorageTest();
        new SortedArrayStorageTest();
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void getLastResume() {
        Resume resume = new Resume(UUID_3);
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void getFirstResume() {
        Resume resume = new Resume(UUID_1);
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }


    @Test(expected = StorageException.class)
    public void checkStorageOverflow() throws Exception {
        Resume[] fullStorage = storage.getFullStorage();
        for (int i = 3; i < fullStorage.length; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                fail("Early storage overflow");
            }
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_3);
        storage.update(resume);
        assertEquals(storage.get(resume.getUuid()), storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume resume = new Resume("dummy");
        storage.update(resume);
    }

    @Test
    public void getAll() {
        assertEquals(storage.size(), storage.getAll().length);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteFirstElem() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteLastElem() {
        storage.delete(UUID_3);
        storage.get(UUID_3);
        assertEquals(2, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }
}