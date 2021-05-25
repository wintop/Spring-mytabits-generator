import getters from '@/store/getters'

export function getCurrDatabase() {
  return getters.currDatabase
}

export function getCurrTable() {
  return getters.currTable
}

export function setCurrDatabase(_this, database) {
  _this.$store.dispatch('opData/setDatabase', database)
}

export function setCurrTable(_this, table) {
  _this.$store.dispatch('opData/setDbTable', table)
}
