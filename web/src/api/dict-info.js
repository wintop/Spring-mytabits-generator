import request from '@/utils/request'

export function getDictInfoList() {
  return request({
    url: 'dict_info/list',
    method: 'post'
  })
}

export function getDictInfo(id) {
  return request({
    url: 'dict_info/get',
    method: 'post',
    params: { id }
  })
}

export function addDictInfo(data) {
  return request({
    url: 'dict_info/add',
    method: 'post',
    data
  })
}

export function editDictInfo(data) {
  return request({
    url: 'dict_info/edit',
    method: 'post',
    data
  })
}

export function editDictInfoStatus(id, status) {
  return request({
    url: 'dict_info/edit_status',
    method: 'post',
    params: { id, status }
  })
}
