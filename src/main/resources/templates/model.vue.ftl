<template>
  <div class="main-wrapper">
    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="名称" class="filter-item" @keyup.enter.native="handleFilter"/>
      <el-select v-model="listQuery.status" placeholder="状态" clearable class="filter-item">
        <el-option v-for="item in statusOptions" :key="item.key" :label="item.statusName" :value="item.key"/>
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row>
<#if columns??>
  <#list columns as column>
      <el-table-column label="${column.remarks}" align="center">
        <template slot-scope="{row}">
          <#if column.columnName == 'status'>
          <el-tag :type="row.status | styleFilter()">{{ row.status | statusFilter() }}</el-tag>
          <#elseif column.typeName = 'Date'>
          <span>{{ row.${column.propertyName} | timeFilter('{y}-{m}-{d} {h}:{i}') }}</span>
          <#else>
          {{ row.${column.propertyName} }}
          </#if>
        </template>
      </el-table-column>
  </#list>
</#if>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button size="mini" type="success" @click="handleDetail(row)">详情</el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(row)">修改</el-button>
          <el-button v-if="row.status!=0" size="mini" type="danger" @click="del${className}(row)">删除</el-button>
          <el-button v-if="row.status!=1" size="mini" type="success" @click="reuse${className}(row)">恢复</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="fetch${className}List" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="${objName}Data" label-position="left" label-width="90px">
      <#if columns??>
        <#list columns as column>
        <el-form-item label="${column.remarks}">
          <#if column.columnName == 'status' >
          <el-select v-model="${objName}Data.status" class="filter-item" placeholder="请选择">
            <el-option v-for="item in statusOptions" :key="item.key" :label="item.statusName" :value="item.key"/>
          </el-select>
          <#else>
          <el-input v-model="${objName}Data.${column.propertyName}" placeholder="请填写${column.remarks}"/>
          </#if>
        </el-form-item>
        </#list>
      </#if>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?create${className}():update${className}()">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="详情" :visible.sync="dialogDetailVisible">
      <el-form ref="dataForm" :model="${objName}Data" label-position="left" label-width="90px">
        <#if columns??>
          <#list columns as column>
            <el-form-item label="${column.remarks}">
            <#if column.columnName == 'status' >
              <el-tag :type="row.status | styleFilter()">{{ row.status | statusFilter() }}</el-tag>
            <#elseif column.typeName == 'Date'>
              <span>{{ row.${column.propertyName} | timeFilter('{y}-{m}-{d} {h}:{i}') }}</span>
            <#else>
              <span>{{ row.${column.propertyName} }}</span>
            </#if>
            </el-form-item>
          </#list>
        </#if>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { get${className}List, add${className}, edit${className}, edit${className}Status } from '@/api/${objName}'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'

export default {
  name: '${className}',
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
        limit: 20,
      },
      statusOptions: [
        { key: 0, statusName: '无效' },
        { key: 1, statusName: '有效' }
      ],
      ${objName}Data: {},
      dialogFormVisible: false,
      dialogDetailVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '添加'
      },
      rules: {},
    }
  },
  created() {
    this.fetch${className}List()
  },
  methods: {
    fetch${className}List() {
      this.listLoading = true
      get${className}List(this.listQuery).then(response => {
        this.list = response.data
        this.total = response.total
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.get${className}List()
    },
    reset${className}Data() {
        this.${className}Data = {}
    },
    handleDetail() {
      this.${className}Data = Object.assign({}, row)
      this.dialogDetailVisible = true
    },
    handleCreate() {
      this.reset${className}Data()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    create${className}() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          add${className}(this.${className}Data).then((response) => {
            this.fetch${className}List()
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
      this.${className}Data = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    update${className}() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.${className}Data)
          edit${className}(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.${className}Data.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.${className}Data)
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
    del${className}(row) {
      row.status = 0
      update${className}Status(row, 0)
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success',
        duration: 2000
      })
      const index = this.list.indexOf(row)
      this.list.splice(index, 1)
    },
    reuse${className}(row) {
      row.status = 1
      update${className}Status(row, 1)
      this.$notify({
        title: '成功',
        message: '数据恢复成功',
        type: 'success',
        duration: 2000
      })
      const index = this.list.indexOf(row)
      this.list.splice(index, 1)
    },
    update${className}Status(row, status) {
      row.status = status
        edit${className}Status(row.lid, status).then(() => {
        this.${className}Data = Object.assign({}, row)
        this.${className}Data.status = status
        for (const v of this.list) {
          if (v.id === this.${className}Data.id) {
            const index = this.list.indexOf(v)
            this.list.splice(index, 1, this.${className}Data)
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
