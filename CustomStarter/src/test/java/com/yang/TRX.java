package com.yang;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TRX {

    @Test
    Object testTrx() {
        ReadView readView = new ReadView();
        // 从链表的头部开始遍历
        for (RowData data = new RowData(); data.DB_ROLL_PTR != null; data = data.DB_ROLL_PTR) {
            if (data.trx_id == readView.creator_trx_id ||
                    (data.trx_id >= readView.min_trx_id && data.trx_id <= readView.max_trx_id && !readView.m_ids.contains(data.trx_id)) ||
                    data.trx_id < readView.min_trx_id
            ) {
                return data;
            }
        }
        return null;
    }

    static class RowData {
        private int trx_id;
        private RowData DB_ROLL_PTR;
    }

    static class ReadView {
        private int creator_trx_id;
        private int min_trx_id;
        private int max_trx_id;
        private List<Integer> m_ids;
    }
}
