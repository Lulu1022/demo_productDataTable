create table vendors
(
    vendor_id int not null comment '廠商編號ID'
        primary key
)
    comment '廠商資料表';

create table products
(
    product_id      int          not null comment '產品編號ID'
        primary key,
    vendor_id       int          null comment '廠商編號',
    product_content varchar(255) null comment '商品描述',
    product_name    varchar(100) null comment '商品名稱',
    price           int          null comment '商品價格',
    product_spec    varchar(100) null comment '商品規格',
    stock           int          null comment '商品庫存',
    created_time    timestamp    null comment '上架時間',
    updated_time    timestamp    null comment '下架時間',
    product_type    int          null comment '商品狀態',
    updated_at      timestamp    null comment '商品資料更新時間',
    address         varchar(255) null comment '交易地址',
    constraint products_ibfk_1
        foreign key (vendor_id) references vendors (vendor_id)
)
    comment '商品資料表';

create table product_picture
(
    picture_id   int          not null comment '圖片編號'
        primary key,
    product_id   int          null comment '產品編號 (外鍵)',
    picture_name varchar(100) null comment '圖片名稱',
    product      longblob     null,
    constraint product_picture_products_product_id_fk
        foreign key (product_id) references products (product_id)
            on delete cascade
)
    comment '商品圖片表';

create index vendor_id
    on products (vendor_id);


