CREATE TABLE account_transaction(
  account_transaction_origination_dt date NOT NULL,
  account_transaction_sys_id char(32) NOT NULL,
  account_transaction_cd smallint NOT NULL,
  account_transaction_dt date NOT NULL,
  account_transaction_trans_subtyp_cd smallint DEFAULT NULL,
  correspondence_dt date DEFAULT NULL,
  quality_cd smallint NOT NULL,
  quality_sys_id char(32) DEFAULT NULL,
  document_number char(14) DEFAULT NULL,
  ext_source char(8) NOT NULL,
  identification_cd smallint NOT NULL,
  input_file char(44) DEFAULT NULL,
  partition_sys_id char(32) NOT NULL,
  period_end_dt date DEFAULT NULL,
  posted_cyc_id char(16) DEFAULT NULL,
  pin char(9) NOT NULL,
  unpostable_cd smallint DEFAULT NULL,
  unpostable_rsn_cd smallint DEFAULT NULL,
  updated_by_trans char(8) NOT NULL,
  updated_ts timestamp NOT NULL,
  validity_cd smallint NOT NULL
);

CREATE TABLE input_transaction (
  account_transaction_sys_id char(32) NOT NULL,
  input_transaction_txt long varchar NOT NULL,
  partition_sys_id char(32) NOT NULL,
  updated_by_trans char(8) NOT NULL,
  updated_ts timestamp NOT NULL
);

CREATE TABLE customer (
  customer_sys_id char(32) NOT NULL,
  account_transaction_sys_id char(32) NOT NULL,
  pin char(9) NOT NULL,
  memo_freeze_cd smallint NOT NULL,
  validity_cd smallint NOT NULL
);