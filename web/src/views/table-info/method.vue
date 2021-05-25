<template>
  <div class="main-wrapper">
    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="名称" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.status" placeholder="状态" clearable class="filter-item">
        <el-option v-for="item in statusOptions" :key="item.key" :label="item.statusName" :value="item.key" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row>
      <el-table-column label="主键ID" align="center">
        <template slot-scope="{row}">
          {{ row.id }}
        </template>
      </el-table-column>
      <el-table-column label="方法名称" align="center">
        <template slot-scope="{row}">
          {{ row.name }}
        </template>
      </el-table-column>
      <el-table-column label="方法调用方式" align="center">
        <template slot-scope="{row}">
          {{ row.requestType }}
        </template>
      </el-table-column>
      <el-table-column label="路径传参" align="center">
        <template slot-scope="{row}">
          {{ row.pathVariable }}
        </template>
      </el-table-column>
      <el-table-column label="表ID" align="center">
        <template slot-scope="{row}">
          {{ row.tableId }}
        </template>
      </el-table-column>
      <el-table-column label="所属数据库" align="center">
        <template slot-scope="{row}">
          {{ row.dbId }}
        </template>
      </el-table-column>
      <el-table-column label="所属用户" align="center">
        <template slot-scope="{row}">
          {{ row.userId }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="{row}">
          {{ row.createTime }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button size="mini" type="success" @click="handleDetail(row)">详情</el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(row)">修改</el-button>
          <el-button v-if="row.status!=0" size="mini" type="danger" @click="delTableMethod(row)">删除</el-button>
          <el-button v-if="row.status!=1" size="mini" type="success" @click="reuseTableMethod(row)">恢复</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="fetchTableMethodList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="tableMethodData" label-position="left" label-width="90px">
        <el-form-item label="主键ID">
          <el-input v-model="tableMethodData.id" placeholder="请填写主键ID" />
        </el-form-item>
        <el-form-item label="方法名称 get, find, save, update, remove, total, pagination">
          <el-input v-model="tableMethodData.name" placeholder="请填写方法名称 get, find, save, update, remove, total, pagination" />
        </el-form-item>
        <el-form-item label="方法的调用方式method：POST, GET, PUT, DELETE">
          <el-input v-model="tableMethodData.requestType" placeholder="请填写方法的调用方式method：POST, GET, PUT, DELETE" />
        </el-form-item>
        <el-form-item label="是否需要在路径中传入参数">
          <el-input v-model="tableMethodData.pathVariable" placeholder="请填写是否需要在路径中传入参数" />
        </el-form-item>
        <el-form-item label="表ID">
          <el-input v-model="tableMethodData.tableId" placeholder="请填写表ID" />
        </el-form-item>
        <el-form-item label="所属数据库">
          <el-input v-model="tableMethodData.dbId" placeholder="请填写所属数据库" />
        </el-form-item>
        <el-form-item label="所属用户">
          <el-input v-model="tableMethodData.userId" placeholder="请填写所属用户" />
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input v-model="tableMethodData.createTime" placeholder="请填写创建时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createTableMethod():updateTableMethod()">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="详情" :visible.sync="dialogDetailVisible">
      <el-form ref="dataForm" :model="tableMethodData" label-position="left" label-width="90px">
        <el-form-item label="主键ID">
          <span>{{ tableMethodData.id }}</span>
        </el-form-item>
        <el-form-item label="方法名称 get, find, save, update, remove, total, pagination">
          <span>{{ tableMethodData.name }}</span>
        </el-form-item>
        <el-form-item label="方法的调用方式method：POST, GET, PUT, DELETE">
          <span>{{ tableMethodData.requestType }}</span>
        </el-form-item>
        <el-form-item label="是否需要在路径中传入参数">
          <span>{{ tableMethodData.pathVariable }}</span>
        </el-form-item>
        <el-form-item label="表ID">
          <span>{{ tableMethodData.tableId }}</span>
        </el-form-item>
        <el-form-item label="所属数据库">
          <span>{{ tableMethodData.dbId }}</span>
        </el-form-item>
        <el-form-item label="所属用户">
          <span>{{ tableMethodData.userId }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ tableMethodData.createTime }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getTableMethodList, addTableMethod, editTableMethod, editTableMethodStatus } from '@/api/table-method'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'

export default {
  name: 'TableMethod',
  components: {
    Pagination
  },
  filters: {
    styleFilter(style) {
      const styleMap = { 1: 'success', 0: 'danger' }
      return styleMap[style]
    },
    timeFilter(time, format) {
      return parseTime(time, format)
    },
    statusFilter(value) {
      const statusMap = { 0: '无效', 1: '有效' }
      return statusMap[value]
    }
  },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      },
      statusOptions: [
        { key: 0, statusName: '无效' },
        { key: 1, statusName: '有效' }
      ],
      tableMethodData: {},
      dialogFormVisible: false,
      dialogDetailVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '添加'
      },
      rules: {}
    }
  },
  created() {
    this.fetchTableMethodList()
  },
  methods: {
    fetchTableMethodList() {
      this.listLoading = true
      getTableMethodList(this.listQuery).then(response => {
        this.list = response.data
        this.total = response.total
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchTableMethodList()
    },
    resetTableMethodData() {
      this.tableMethodData = {}
    },
    handleDetail(row) {
      this.tableMethodData = Object.assign({}, row)
      this.dialogDetailVisible = true
    },
    handleCreate() {
      this.resetTableMethodData()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createTableMethod() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          addTableMethod(this.tableMethodData).then((response) => {
            this.fetchTableMethodList()
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.tableMethodData = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateTableMethod() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.tableMethodData)
          editTableMethod(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.tableMethodData.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.tableMethodData)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    delTableMethod(row) {
      row.status = 0
      this.updateTableMethodStatus(row, 0).then(() => {
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
        const index = this.list.indexOf(row)
        this.list.splice(index, 1)
      })
    },
    reuseTableMethod(row) {
      row.status = 1
      this.updateTableMethodStatus(row, 1).then(() => {
        this.$notify({
          title: '成功',
          message: '数据恢复成功',
          type: 'success',
          duration: 2000
        })
        const index = this.list.indexOf(row)
        this.list.splice(index, 1)
      })
    },
    updateTableMethodStatus(row, status) {
      row.status = status
      editTableMethodStatus(row.lid, status).then(() => {
        this.tableMethodData = Object.assign({}, row)
        this.tableMethodData.status = status
        for (const v of this.list) {
          if (v.id === this.tableMethodData.id) {
            const index = this.list.indexOf(v)
            this.list.splice(index, 1, this.tableMethodData)
            break
          }
        }
      })
      this.$message({
        message: '操作成功',
        type: 'success'
      })
    }
  }
}
</script>
<style scoped>
.main-wrapper {
  margin: 8px;
  .filter-container {
    padding: 10px 0;
    .filter-item {
      width: 200px;
      margin-right: 10px;
    }
  }
}
</style>
