import request from '@/utils/request'

export function getTableInfoList() {
  return request({
    url: 'table_info/list',
    method: 'post'
  })
}

export function getTableInfoPage(pageNo, pageSize) {
  return request({
    url: 'table_info/page',
    method: 'post',
    params: { pageNo, pageSize }
  })
}

export function getTableInfo(id) {
  return request({
    url: 'table_info/get',
    method: 'post',
    params: { id }
  })
}

export function addTableInfo(data) {
  return request({
    url: 'table_info/add',
    method: 'post',
    data
  })
}

export function editTableInfo(data) {
  return request({
    url: 'table_info/edit',
    method: 'post',
    data
  })
}

export function editTableInfoStatus(id, status) {
  return request({
    url: 'table_info/edit_status',
    method: 'post',
    params: { id, status }
  })
}
