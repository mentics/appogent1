package com.mentics.model;

public interface Modify<A> {
    Modification<A> apply(A oldValue);
}
