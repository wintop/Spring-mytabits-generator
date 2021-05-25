import request from '@/utils/request'

export function getDataBaseList() {
  return request({
    url: 'data_base/list',
    method: 'post'
  })
}

export function getDataBase(id) {
  return request({
    url: 'data_base/get',
    method: 'post',
    params: { id }
  })
}

export function addDataBase(data) {
  return request({
    url: 'data_base/add',
    method: 'post',
    data
  })
}

export function editDataBase(data) {
  return request({
    url: 'data_base/edit',
    method: 'post',
    data
  })
}

export function editDataBaseStatus(id, status) {
  return request({
    url: 'data_base/edit_status',
    method: 'post',
    params: { id, status }
  })
}

export function getDatabases(data) {
  return request({
    url: 'data_base/get_dbs',
    method: 'post',
    data
  })
}

export function getDbTables(data) {
  return request({
    url: 'data_base/tables',
    method: 'post',
    data
  })
}

export function copyDbTables(data) {
  return request({
    url: 'data_base/copy_table',
    method: 'post',
    data
  })
}
