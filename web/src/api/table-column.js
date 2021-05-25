import request from '@/utils/request'

export function getTableColumnList(tableId) {
  return request({
    url: 'table_column/list',
    method: 'post',
    params: { tableId }
  })
}

export function getTableColumn(id) {
  return request({
    url: 'table_column/get',
    method: 'post',
    params: { id }
  })
}

export function addTableColumn(data) {
  return request({
    url: 'table_column/add',
    method: 'post',
    data
  })
}

export function editTableColumn(data) {
  return request({
    url: 'table_column/edit',
    method: 'post',
    data
  })
}

export function getColumnFromDb(tableId) {
  return request({
    url: 'table_column/get_db_column',
    method: 'post',
    params: { tableId }
  })
}

export function copyColumnToTable(data) {
  return request({
    url: 'table_column/copy_column',
    method: 'post',
    data
  })
}
