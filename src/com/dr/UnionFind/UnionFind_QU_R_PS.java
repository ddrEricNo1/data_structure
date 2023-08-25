package com.dr.UnionFind;
// 路径分裂
public class UnionFind_QU_R_PS extends UnionFind_QU_R {

    public UnionFind_QU_R_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[p];
            v = p;
        }
        return v;
    }
}
