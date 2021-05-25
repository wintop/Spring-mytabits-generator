export const dbTypeList = [
  { value: 'Mysql', name: 'Mysql', disabled: false },
  { value: 'PostgreSQL', name: 'PostgreSQL', disabled: true },
  { value: 'Oracle', name: 'Oracle', disabled: true }
]

export const statusList = [
  { key: 0, statusName: '无效' },
  { key: 1, statusName: '有效' }
]

export const statusMap = { 0: '无效', 1: '有效' }

export function getStatusName(status) {
  return statusMap[status]
}
