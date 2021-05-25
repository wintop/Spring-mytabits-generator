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
      <el-table-column label="所属表ID" align="center">
        <template slot-scope="{row}">
          {{ row.tableId }}
        </template>
      </el-table-column>
      <el-table-column label="索引名称" align="center">
        <template slot-scope="{row}">
          {{ row.name }}
        </template>
      </el-table-column>
      <el-table-column label="索引类型: key, primary key, unique key" align="center">
        <template slot-scope="{row}">
          {{ row.type }}
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
          <el-button v-if="row.status!=0" size="mini" type="danger" @click="delTableIndex(row)">删除</el-button>
          <el-button v-if="row.status!=1" size="mini" type="success" @click="reuseTableIndex(row)">恢复</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="fetchTableIndexList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="tableIndexData" label-position="left" label-width="90px">
        <el-form-item label="主键ID">
          <el-input v-model="tableIndexData.id" placeholder="请填写主键ID" />
        </el-form-item>
        <el-form-item label="所属表ID">
          <el-input v-model="tableIndexData.tableId" placeholder="请填写所属表ID" />
        </el-form-item>
        <el-form-item label="索引名称">
          <el-input v-model="tableIndexData.name" placeholder="请填写索引名称" />
        </el-form-item>
        <el-form-item label="索引类型: key, primary key, unique key">
          <el-input v-model="tableIndexData.type" placeholder="请填写索引类型: key, primary key, unique key" />
        </el-form-item>
        <el-form-item label="所属数据库">
          <el-input v-model="tableIndexData.dbId" placeholder="请填写所属数据库" />
        </el-form-item>
        <el-form-item label="所属用户">
          <el-input v-model="tableIndexData.userId" placeholder="请填写所属用户" />
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input v-model="tableIndexData.createTime" placeholder="请填写创建时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createTableIndex():updateTableIndex()">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="详情" :visible.sync="dialogDetailVisible">
      <el-form ref="dataForm" :model="tableIndexData" label-position="left" label-width="90px">
        <el-form-item label="主键ID">
          <span>{{ tableIndexData.id }}</span>
        </el-form-item>
        <el-form-item label="所属表ID">
          <span>{{ tableIndexData.tableId }}</span>
        </el-form-item>
        <el-form-item label="索引名称">
          <span>{{ tableIndexData.name }}</span>
        </el-form-item>
        <el-form-item label="索引类型: key, primary key, unique key">
          <span>{{ tableIndexData.type }}</span>
        </el-form-item>
        <el-form-item label="所属数据库">
          <span>{{ tableIndexData.dbId }}</span>
        </el-form-item>
        <el-form-item label="所属用户">
          <span>{{ tableIndexData.userId }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ tableIndexData.createTime }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getTableIndexList, addTableIndex, editTableIndex, editTableIndexStatus } from '@/api/table-index'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'

export default {
  name: 'TableIndex',
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
      tableIndexData: {},
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
    this.fetchTableIndexList()
  },
  methods: {
    fetchTableIndexList() {
      this.listLoading = true
      getTableIndexList(this.listQuery).then(response => {
        this.list = response.data
        this.total = response.total
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchTableIndexList()
    },
    resetTableIndexData() {
      this.tableIndexData = {}
    },
    handleDetail(row) {
      this.tableIndexData = Object.assign({}, row)
      this.dialogDetailVisible = true
    },
    handleCreate() {
      this.resetTableIndexData()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createTableIndex() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          addTableIndex(this.tableIndexData).then((response) => {
            this.fetchTableIndexList()
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
      this.tableIndexData = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateTableIndex() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.tableIndexData)
          editTableIndex(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.tableIndexData.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.tableIndexData)
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
    delTableIndex(row) {
      row.status = 0
      this.updateTableIndexStatus(row, 0).then(() => {
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
    reuseTableIndex(row) {
      row.status = 1
      this.updateTableIndexStatus(row, 1).then(() => {
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
    updateTableIndexStatus(row, status) {
      row.status = status
      editTableIndexStatus(row.lid, status).then(() => {
        this.tableIndexData = Object.assign({}, row)
        this.tableIndexData.status = status
        for (const v of this.list) {
          if (v.id === this.tableIndexData.id) {
            const index = this.list.indexOf(v)
            this.list.splice(index, 1, this.tableIndexData)
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
