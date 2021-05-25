<template>
  <div class="main-wrapper">
    <div class="filter-container">
      <el-select v-model="listQuery.status" placeholder="状态" clearable class="filter-item">
        <el-option v-for="item in statusOptions" :key="item.key" :label="item.statusName" :value="item.key" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row>
      <el-table-column label="表名" align="center">
        <template slot-scope="{row}">
          {{ row.name }}
        </template>
      </el-table-column>
      <el-table-column label="展示名称" align="center">
        <template slot-scope="{row}">
          {{ row.label }}
        </template>
      </el-table-column>
      <el-table-column label="引擎" align="center">
        <template slot-scope="{row}">
          {{ row.engine }}
        </template>
      </el-table-column>
      <el-table-column label="数据类型" align="center">
        <template slot-scope="{row}">
          {{ row.charset }}
        </template>
      </el-table-column>
      <el-table-column label="展示方式" align="center">
        <template slot-scope="{row}">
          {{ row.detailShow }}
        </template>
      </el-table-column>
      <el-table-column label="所属数据库" align="center">
        <template slot-scope="{row}">
          {{ row.dbId }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button size="mini" type="success" @click="handleDetail(row)">详情</el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(row)">修改</el-button>
          <router-link :to="'/table-info/column/'+row.id">
            <el-button type="primary" size="mini">字段信息</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="fetchTableInfoList" />

    <el-dialog top="3vh" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="tableInfoData" label-position="left" label-width="120px">
        <el-form-item label="表名">
          <el-input v-model="tableInfoData.name" placeholder="请填写表名" />
        </el-form-item>
        <el-form-item label="前端展示名称">
          <el-input v-model="tableInfoData.label" placeholder="请填写前端展示名称" />
        </el-form-item>
        <el-form-item label="引擎 InnoDb">
          <el-input v-model="tableInfoData.engine" placeholder="请填写引擎 InnoDb" />
        </el-form-item>
        <el-form-item label="数据类型">
          <el-input v-model="tableInfoData.charset" placeholder="请填写数据类型" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="tableInfoData.comment" placeholder="请填写备注" />
        </el-form-item>
        <el-form-item label="是否需要缓存">
          <el-input v-model="tableInfoData.cache" placeholder="请填写是否需要缓存" />
        </el-form-item>
        <el-form-item label="是否返回ID">
          <el-input v-model="tableInfoData.retPrimary" placeholder="请填写是否返回ID" />
        </el-form-item>
        <el-form-item label="增改展示方式">
          <el-input v-model="tableInfoData.auShow" placeholder="请填写数据创建(add)修改(update)展示方式 row 行内修改 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面" />
        </el-form-item>
        <el-form-item label="数据查看展示">
          <el-input v-model="tableInfoData.detailShow" placeholder="请填写数据查看展示方式 null 不用展示 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面" />
        </el-form-item>
        <el-form-item label="所属数据库">
          <el-input v-model="tableInfoData.dbId" placeholder="请填写所属数据库" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createTableInfo():updateTableInfo()">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="详情" :visible.sync="dialogDetailVisible">
      <el-form ref="dataForm" :model="tableInfoData" label-position="left" label-width="90px">
        <el-form-item label="主键ID">
          <span>{{ tableInfoData.id }}</span>
        </el-form-item>
        <el-form-item label="表名">
          <span>{{ tableInfoData.name }}</span>
        </el-form-item>
        <el-form-item label="前端展示名称">
          <span>{{ tableInfoData.label }}</span>
        </el-form-item>
        <el-form-item label="引擎 InnoDb">
          <span>{{ tableInfoData.engine }}</span>
        </el-form-item>
        <el-form-item label="数据类型">
          <span>{{ tableInfoData.charset }}</span>
        </el-form-item>
        <el-form-item label="备注">
          <span>{{ tableInfoData.comment }}</span>
        </el-form-item>
        <el-form-item label="是否需要缓存">
          <span>{{ tableInfoData.cache }}</span>
        </el-form-item>
        <el-form-item label="是否返回ID">
          <span>{{ tableInfoData.retPrimary }}</span>
        </el-form-item>
        <el-form-item label="数据创建(add)修改(update)展示方式 row 行内修改 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面">
          <span>{{ tableInfoData.auShow }}</span>
        </el-form-item>
        <el-form-item label="数据查看展示方式 null 不用展示 expand 展开行显示 dialog 弹框 drawer 右侧-抽屉 page 独立页面">
          <span>{{ tableInfoData.detailShow }}</span>
        </el-form-item>
        <el-form-item label="所属数据库">
          <span>{{ tableInfoData.dbId }}</span>
        </el-form-item>
        <el-form-item label="所属用户">
          <span>{{ tableInfoData.userId }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ tableInfoData.createTime }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getTableInfoPage, addTableInfo, editTableInfo, editTableInfoStatus } from '@/api/table-info'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'

export default {
  name: 'TableInfo',
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
      tableInfoData: {},
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
    this.fetchTableInfoList()
  },
  methods: {
    fetchTableInfoList() {
      this.listLoading = true
      getTableInfoPage(this.listQuery.page, this.listQuery.limit).then(response => {
        this.list = response.data.data
        this.total = response.data.total
        setTimeout(() => {
          this.listLoading = false
        })
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchTableInfoList()
    },
    resetTableInfoData() {
      this.tableInfoData = {}
    },
    handleDetail(row) {
      this.tableInfoData = Object.assign({}, row)
      this.dialogDetailVisible = true
    },
    handleCreate() {
      this.resetTableInfoData()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createTableInfo() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          addTableInfo(this.tableInfoData).then((response) => {
            this.fetchTableInfoList()
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
      this.tableInfoData = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateTableInfo() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.tableInfoData)
          editTableInfo(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.tableInfoData.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.tableInfoData)
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
    delTableInfo(row) {
      row.status = 0
      this.updateTableInfoStatus(row, 0).then(() => {
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
    reuseTableInfo(row) {
      row.status = 1
      this.updateTableInfoStatus(row, 1).then(() => {
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
    updateTableInfoStatus(row, status) {
      row.status = status
      editTableInfoStatus(row.lid, status).then(() => {
        this.tableInfoData = Object.assign({}, row)
        this.tableInfoData.status = status
        for (const v of this.list) {
          if (v.id === this.tableInfoData.id) {
            const index = this.list.indexOf(v)
            this.list.splice(index, 1, this.tableInfoData)
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
