package com.matthewoks.firstStep.Repositories;

import java.util.List;

public interface IRepositoryRead<T> {
//t consente di omettere l'oggetto , ora è dinamico non hardcodato
    T getById(int id);
    List<T> getAll();

}
