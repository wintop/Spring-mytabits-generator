import Cookies from 'js-cookie'

const state = {
  currDatabase: Cookies.get('currDatabase') || '',
  currTable: Cookies.get('currTable') || ''
}

const mutations = {
  SET_DATABASE: (state, database) => {
    state.currDatabase = database
    Cookies.set('currDatabase', database)
  },
  SET_DB_TABLE: (state, table) => {
    state.currTable = table
    Cookies.set('currTable', table)
  }
}

const actions = {
  setDatabase({ commit }, database) {
    commit('SET_DATABASE', database)
  },
  setDbTable({ commit }, table) {
    commit('SET_DB_TABLE', table)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
