import request from '@/utils/request'

export function getUserAccountList() {
  return request({
    url: 'user_account/list',
    method: 'post'
  })
}

export function getUserAccount(id) {
  return request({
    url: 'user_account/get',
    method: 'post',
    params: { id }
  })
}

export function addUserAccount(data) {
  return request({
    url: 'user_account/add',
    method: 'post',
    data
  })
}

export function editUserAccount(data) {
  return request({
    url: 'user_account/edit',
    method: 'post',
    data
  })
}

export function editUserAccountStatus(id, status) {
  return request({
    url: 'user_account/edit_status',
    method: 'post',
    params: { id, status }
  })
}
