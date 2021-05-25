import request from '@/utils/request'

export function getDictConfigList() {
  return request({
    url: 'dict_config/list',
    method: 'post'
  })
}

export function getDictConfig(id) {
  return request({
    url: 'dict_config/get',
    method: 'post',
    params: { id }
  })
}

export function addDictConfig(data) {
  return request({
    url: 'dict_config/add',
    method: 'post',
    data
  })
}

export function editDictConfig(data) {
  return request({
    url: 'dict_config/edit',
    method: 'post',
    data
  })
}

export function editDictConfigStatus(id, status) {
  return request({
    url: 'dict_config/edit_status',
    method: 'post',
    params: { id, status }
  })
}
