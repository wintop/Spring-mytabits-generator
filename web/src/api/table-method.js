import request from '@/utils/request'

export function getTableMethodList() {
  return request({
    url: 'table_method/list',
    method: 'post'
  })
}

export function getTableMethod(id) {
  return request({
    url: 'table_method/get',
    method: 'post',
    params: { id }
  })
}

export function addTableMethod(data) {
  return request({
    url: 'table_method/add',
    method: 'post',
    data
  })
}

export function editTableMethod(data) {
  return request({
    url: 'table_method/edit',
    method: 'post',
    data
  })
}

export function editTableMethodStatus(id, status) {
  return request({
    url: 'table_method/edit_status',
    method: 'post',
    params: { id, status }
  })
}
