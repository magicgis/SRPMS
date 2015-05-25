package service;

import entity.Basepaper;

import java.util.List;

public interface BasepaperService extends BaseService<Basepaper> {
    List<Basepaper> search(String keyword, String sort, String order);
}