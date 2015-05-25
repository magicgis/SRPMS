package service;

import entity.Mag;

import java.util.List;

public interface MagService extends BaseService<Mag> {
    List<Mag> search(String keyword, String sort, String order);
}