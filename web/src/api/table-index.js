import request from '@/utils/request'

export function getTableIndexList() {
  return request({
    url: 'table_index/list',
    method: 'post'
  })
}

export function getTableIndex(id) {
  return request({
    url: 'table_index/get',
    method: 'post',
    params: { id }
  })
}

export function addTableIndex(data) {
  return request({
    url: 'table_index/add',
    method: 'post',
    data
  })
}

export function editTableIndex(data) {
  return request({
    url: 'table_index/edit',
    method: 'post',
    data
  })
}

export function editTableIndexStatus(id, status) {
  return request({
    url: 'table_index/edit_status',
    method: 'post',
    params: { id, status }
  })
}
