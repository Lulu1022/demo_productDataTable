// 初始化 DataTable1
function initDataTable1() {
    new DataTable('#myTable1', {
        ajax: 'http://localhost:8080/products',
        processing: true,
        serverSide: true,
        columns: [
            {
                data: null,
                title: '<input type="checkbox" id="select-all">',
                render: function (data, type, row) {
                    return '<input class="form-check-input" type="checkbox" value="' + row.id + '" id="flexCheckDefault' + row.id + '" onchange="handleCheckboxChange(this)">';
                }
            },
            { data: 'productId', title: '編號' },
            { data: 'productName', title: '商品名稱' },
            { data: 'price', title: '價錢' },
            { data: 'stock', title: '剩餘庫存' },
            { data: 'picture', title: '圖片' },
            // {
            //     data: 'status', title: 'Status',
            //     render: function (data, type, row) {
            //         return '<span class="badge bg-secondary">下架</span>';
            //     }
            // },
            {
                data: null, title: '操作',
                render: function (data, type, row) {
                    return '<button type="button" class="btn btn-success btn-sm me-3" onclick="removeItem(' + row.id + ')">下架</button>' +
                        '<a class="btn btn-sm btn-primary me-3" href="edit.html?id=' + row.id + '" role="button" data-bs-target="#popup">編輯</a>' +
                        '<button type="button" class="btn btn-danger btn-sm me-3" onclick="deleteItem(' + row.id + ')">刪除</button>';
                }
            }
        ],
        paging: true,
        columnDefs: [{ orderable: false, targets: [0, 2, 4, 5] }],
        order: [[1, 'asc']]
    });
}

// 初始化 DataTable2
function initDataTable2() {
    new DataTable('#myTable2', {
        ajax: 'http://localhost:3000/mylist',
        columns: [
            { data: 'id', title: 'Id' },
            { data: 'name', title: 'Name' },
            { data: 'date', title: 'Date' },
            { data: 'status', title: 'Status' }
        ],
        dom: 'Bfrtip',
        buttons: [
            'excelHtml5',
            {
                extend: 'pdfHtml5',
                orientation: 'portrait',
                pageSize: 'LEGAL'
            },
            {
                extend: 'print',
                pageSize: 'LEGAL',
                title: 'Visible rows'
            },
            {
                extend: 'selectAll',
                className: 'selectall',
                action: function (e) {
                    e.preventDefault();
                    table.rows({ page: 'all' }).nodes().each(function () {
                        $(this).removeClass('selected');
                    });
                    table.rows({ search: 'applied' }).nodes().each(function () {
                        $(this).addClass('selected');
                    });
                }
            }
        ]
    });
}

// 綁定全選 Checkbox 事件
function bindCheckboxEvents() {
    document.getElementById('select-all').addEventListener('change', function () {
        var checkboxes = document.querySelectorAll('#myTable1 input[type="checkbox"]');
        checkboxes.forEach(function (checkbox) {
            checkbox.checked = document.getElementById('select-all').checked;
            handleCheckboxChange(checkbox); // 處理每個 checkbox 狀態變化
        });
    });
}


// 處理單個 checkbox 的狀態變化
function handleCheckboxChange(checkbox) {
    // 這裡可以添加處理邏輯，例如更新選中的商品
    console.log("Checkbox " + checkbox.value + " 狀態變化：" + checkbox.checked);
}

// 下架功能
function shelf(id) {
    console.log("下架商品 ID: " + id);
    // 在這裡添加下架商品的邏輯
}

// 綁定批次下架按鈕和再次確認按鈕事件
document.getElementById('removeAll').addEventListener('click', function() {
    const confirmRemoveModal = new bootstrap.Modal(document.getElementById('confirmRemoveModal'));
    confirmRemoveModal.show();

    // 綁定模態框內的確認下架按鈕事件，這樣每次模態框打開時都會綁定
    document.getElementById('confirmShelfModal').addEventListener('click', function() {
        confirmRemoveModal.hide(); // 關閉模態框

        // 收集所有被選中的商品 ID
        const selectedItems = [];
        document.querySelectorAll('#myTable1 input[type="checkbox"]:checked').forEach(function(checkbox) {
            selectedItems.push(checkbox.value);  // 將選中的商品 ID 添加到數組中
        });

        console.log('已下架:',selectedItems);

        // if (selectedItems.length > 0) {
        //     console.log('批次下架的商品 ID: ', selectedItems);
        //
        //     // 發送批次下架請求到後端
        //     fetch('/bulkUnpublish', {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json'
        //         },
        //         body: JSON.stringify({ ids: selectedItems })  // 將選中的 ID 傳到後端
        //     })
        //         .then(response => response.json())
        //         .then(data => {
        //             console.log('批次下架成功', data);
        //             // 重新加載 DataTable1 的數據
        //             $('#myTable1').DataTable().ajax.reload();  // 重新從後端獲取最新資料並更新表格
        //         })
        //         .catch(error => console.error('批次下架失敗', error));
        // } else {
        //     console.log('沒有選中的商品');
        // }
    }, { once: true });  // 使用 { once: true } 確保這個事件只會觸發一次
});

// 綁定下架功能能和彈出模態框邏輯
function removeItem(id) {
    itemIdToRemove = id; // 保存當前要下架的商品 ID
    const removeProductModal = new bootstrap.Modal(document.getElementById('confirmRemoveProductModal'));
    removeProductModal.show(); // 顯示模態框

    // 綁定確認刪除的按鈕邏輯
    document.getElementById('confirmRemoveProductBtn').addEventListener('click', function() {
        if (itemIdToRemove !== null) {
            console.log("確認下架商品 ID: " + itemIdToRemove);
            // 在這裡添加下架商品的實際邏輯，例如發送 API 請求


            itemIdToRemove = null; // 清空 ID
            removeProductModal.hide(); // 關閉模態框
        }
    }, { once: true });  // 使用 { once: true } 確保按鈕事件只執行一次
}


// 綁定刪除功能和彈出模態框邏輯
function deleteItem(id) {
    itemIdToDelete = id; // 保存當前要刪除的商品 ID
    const deleteModal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
    deleteModal.show(); // 顯示模態框

    // 綁定確認刪除的按鈕邏輯
    document.getElementById('confirmDeleteBtn').addEventListener('click', function() {
        if (itemIdToDelete !== null) {
            console.log("確認刪除商品 ID: " + itemIdToDelete);
            // 在這裡添加刪除商品的實際邏輯，例如發送 API 請求
            // fetch(`/deleteProduct/${itemIdToDelete}`, { method: 'DELETE' })
            //     .then(response => response.json())
            //     .then(data => console.log("商品已刪除", data));

            itemIdToDelete = null; // 清空 ID
            deleteModal.hide(); // 關閉模態框
        }
    }, { once: true });  // 使用 { once: true } 確保按鈕事件只執行一次
}



document.addEventListener('DOMContentLoaded', function () {
    initDataTable1();
    initDataTable2();
    bindCheckboxEvents();
});
