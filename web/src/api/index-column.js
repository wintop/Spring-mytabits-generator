import request from '@/utils/request'

export function getIndexColumnList() {
  return request({
    url: 'index_column/list',
    method: 'post'
  })
}

export function getIndexColumn(id) {
  return request({
    url: 'index_column/get',
    method: 'post',
    params: { id }
  })
}

export function addIndexColumn(data) {
  return request({
    url: 'index_column/add',
    method: 'post',
    data
  })
}

export function editIndexColumn(data) {
  return request({
    url: 'index_column/edit',
    method: 'post',
    data
  })
}

export function editIndexColumnStatus(id, status) {
  return request({
    url: 'index_column/edit_status',
    method: 'post',
    params: { id, status }
  })
}
