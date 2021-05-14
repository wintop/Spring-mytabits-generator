import request from '@/utils/request'

export function get${className}List() {
  return request({
    url: '${urlName}/list',
    method: 'post'
  })
}

export function get${className}(id) {
  return request({
    url: '${urlName}/get',
    method: 'post',
    params: { id }
  })
}

export function add${className}(data) {
  return request({
    url: '${urlName}/add',
    method: 'post',
    data
  })
}

export function edit${className}(data) {
  return request({
    url: '${urlName}/edit',
    method: 'post',
    data
  })
}

export function edit${className}Status(id, status) {
  return request({
    url: '${urlName}/edit_status',
    method: 'post',
    params: { id, status }
  })
}
