<template>
  <div class="main-wrapper">
    <div class="filter-container">
      <el-select v-model="listQuery.status" placeholder="状态" clearable class="filter-item">
        <el-option v-for="item in statusOptions" :key="item.key" :label="item.statusName" :value="item.key" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleDbColumn">从数据库中导入</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row>
      <el-table-column label="所属表" align="center">
        <template slot-scope="{row}">
          {{ row.tableId }}
        </template>
      </el-table-column>
      <el-table-column label="字段名" align="center">
        <template slot-scope="{row}">
          {{ row.name }}
        </template>
      </el-table-column>
      <el-table-column label="展示名称" align="center">
        <template slot-scope="{row}">
          {{ row.label }}
        </template>
      </el-table-column>
      <el-table-column label="数据类型" align="center">
        <template slot-scope="{row}">
          {{ row.dataType }}
        </template>
      </el-table-column>
      <el-table-column label="展示顺序" align="center">
        <template slot-scope="{row}">
          {{ row.sort }}
        </template>
      </el-table-column>
      <el-table-column label="是否空" align="center">
        <template slot-scope="{row}">
          {{ row.notNull }}
        </template>
      </el-table-column>
      <el-table-column label="默认值" align="center">
        <template slot-scope="{row}">
          {{ row.defaultValue }}
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="{row}">
          {{ row.comment }}
        </template>
      </el-table-column>
      <el-table-column label="是否可修改" align="center">
        <template slot-scope="{row}">
          {{ row.updateType }}
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
          <el-button v-if="row.status!=0" size="mini" type="danger" @click="delTableColumn(row)">删除</el-button>
          <el-button v-if="row.status!=1" size="mini" type="success" @click="reuseTableColumn(row)">恢复</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="fetchTableColumnList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="tableColumnData" label-position="left" label-width="90px">
        <el-form-item label="主键ID">
          <el-input v-model="tableColumnData.id" placeholder="请填写主键ID" />
        </el-form-item>
        <el-form-item label="所属表ID，不用名字，有重复">
          <el-input v-model="tableColumnData.tableId" placeholder="请填写所属表ID，不用名字，有重复" />
        </el-form-item>
        <el-form-item label="所属数据库">
          <el-input v-model="tableColumnData.dbId" placeholder="请填写所属数据库" />
        </el-form-item>
        <el-form-item label="字段名">
          <el-input v-model="tableColumnData.name" placeholder="请填写字段名" />
        </el-form-item>
        <el-form-item label="展示名称，考虑到comment中内容比较丰富，增加此字段">
          <el-input v-model="tableColumnData.label" placeholder="请填写展示名称，考虑到comment中内容比较丰富，增加此字段" />
        </el-form-item>
        <el-form-item label="数据类型(int, bigint, tinyint, varchar, datetime, date, timestamp)">
          <el-input v-model="tableColumnData.dataType" placeholder="请填写数据类型(int, bigint, tinyint, varchar, datetime, date, timestamp)" />
        </el-form-item>
        <el-form-item label="字段数据的长度，一般用于字符串和数值型，varchar(10), int(11)">
          <el-input v-model="tableColumnData.dataLength" placeholder="请填写字段数据的长度，一般用于字符串和数值型，varchar(10), int(11)" />
        </el-form-item>
        <el-form-item label="展示顺序">
          <el-input v-model="tableColumnData.sort" placeholder="请填写展示顺序" />
        </el-form-item>
        <el-form-item label="是否空 0 可以为空 1 not null">
          <el-input v-model="tableColumnData.notNull" placeholder="请填写是否空 0 可以为空 1 not null" />
        </el-form-item>
        <el-form-item label="是否自增">
          <el-input v-model="tableColumnData.autoIncrement" placeholder="请填写是否自增" />
        </el-form-item>
        <el-form-item label="默认值">
          <el-input v-model="tableColumnData.defaultValue" placeholder="请填写默认值" />
        </el-form-item>
        <el-form-item label="备注，支持原生">
          <el-input v-model="tableColumnData.comment" placeholder="请填写备注，支持原生" />
        </el-form-item>
        <el-form-item label="页面编辑使用元素 默认使用input">
          <el-input v-model="tableColumnData.editElement" placeholder="请填写页面编辑使用元素 默认使用input" />
        </el-form-item>
        <el-form-item label="(枚举型数据)选择数据，默认为空">
          <el-input v-model="tableColumnData.data" placeholder="请填写(枚举型数据)选择数据，默认为空" />
        </el-form-item>
        <el-form-item label="数据来源 1 空 2 配置 3 查询数据表">
          <el-input v-model="tableColumnData.dataFrom" placeholder="请填写数据来源 1 空 2 配置 3 查询数据表" />
        </el-form-item>
        <el-form-item label="新增数据时传入方式 1 前端传入，2 后台默认, 从dta字段中取">
          <el-input v-model="tableColumnData.addType" placeholder="请填写新增数据时传入方式 1 前端传入，2 后台默认, 从dta字段中取" />
        </el-form-item>
        <el-form-item label="是否可以update 0 不需要update 1 可以">
          <el-input v-model="tableColumnData.updateType" placeholder="请填写是否可以update 0 不需要update 1 可以" />
        </el-form-item>
        <el-form-item label="查询时计算方式(在分页查询时，使用哪种计算方式 EQ: =, GT: >, LT:<, NE: !=, GE: >=, LE: <=, LK: like {}%, LA: like %{}%, NN: not null)">
          <el-input v-model="tableColumnData.queryType" placeholder="请填写查询时计算方式(在分页查询时，使用哪种计算方式 EQ: =, GT: >, LT:<, NE: !=, GE: >=, LE: <=, LK: like {}%, LA: like %{}%, NN: not null)" />
        </el-form-item>
        <el-form-item label="所属用户">
          <el-input v-model="tableColumnData.userId" placeholder="请填写所属用户" />
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input v-model="tableColumnData.createTime" placeholder="请填写创建时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createTableColumn():updateTableColumn()">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="详情" :visible.sync="dialogDetailVisible">
      <el-form ref="dataForm" :model="tableColumnData" label-position="left" label-width="90px">
        <el-form-item label="主键ID">
          <span>{{ tableColumnData.id }}</span>
        </el-form-item>
        <el-form-item label="所属表ID，不用名字，有重复">
          <span>{{ tableColumnData.tableId }}</span>
        </el-form-item>
        <el-form-item label="所属数据库">
          <span>{{ tableColumnData.dbId }}</span>
        </el-form-item>
        <el-form-item label="字段名">
          <span>{{ tableColumnData.name }}</span>
        </el-form-item>
        <el-form-item label="展示名称，考虑到comment中内容比较丰富，增加此字段">
          <span>{{ tableColumnData.label }}</span>
        </el-form-item>
        <el-form-item label="数据类型(int, bigint, tinyint, varchar, datetime, date, timestamp)">
          <span>{{ tableColumnData.dataType }}</span>
        </el-form-item>
        <el-form-item label="字段数据的长度，一般用于字符串和数值型，varchar(10), int(11)">
          <span>{{ tableColumnData.dataLength }}</span>
        </el-form-item>
        <el-form-item label="展示顺序">
          <span>{{ tableColumnData.sort }}</span>
        </el-form-item>
        <el-form-item label="是否空 0 可以为空 1 not null">
          <span>{{ tableColumnData.notNull }}</span>
        </el-form-item>
        <el-form-item label="是否自增">
          <span>{{ tableColumnData.autoIncrement }}</span>
        </el-form-item>
        <el-form-item label="默认值">
          <span>{{ tableColumnData.defaultValue }}</span>
        </el-form-item>
        <el-form-item label="备注，支持原生">
          <span>{{ tableColumnData.comment }}</span>
        </el-form-item>
        <el-form-item label="页面编辑使用元素 默认使用input">
          <span>{{ tableColumnData.editElement }}</span>
        </el-form-item>
        <el-form-item label="(枚举型数据)选择数据，默认为空">
          <span>{{ tableColumnData.data }}</span>
        </el-form-item>
        <el-form-item label="数据来源 1 空 2 配置 3 查询数据表">
          <span>{{ tableColumnData.dataFrom }}</span>
        </el-form-item>
        <el-form-item label="新增数据时传入方式 1 前端传入，2 后台默认, 从dta字段中取">
          <span>{{ tableColumnData.addType }}</span>
        </el-form-item>
        <el-form-item label="是否可以update 0 不需要update 1 可以">
          <span>{{ tableColumnData.updateType }}</span>
        </el-form-item>
        <el-form-item label="查询时计算方式(在分页查询时，使用哪种计算方式 EQ: =, GT: >, LT:<, NE: !=, GE: >=, LE: <=, LK: like {}%, LA: like %{}%, NN: not null)">
          <span>{{ tableColumnData.queryType }}</span>
        </el-form-item>
        <el-form-item label="所属用户">
          <span>{{ tableColumnData.userId }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ tableColumnData.createTime }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getTableColumnList, addTableColumn, editTableColumn } from '@/api/table-column'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'

export default {
  name: 'TableColumn',
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
      tableColumnData: {},
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
    this.fetchTableColumnList()
  },
  methods: {
    fetchTableColumnList() {
      this.listLoading = true
      getTableColumnList(this.listQuery).then(response => {
        this.list = response.data
        this.total = response.total
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchTableColumnList()
    },
    resetTableColumnData() {
      this.tableColumnData = {}
    },
    handleDetail(row) {
      this.tableColumnData = Object.assign({}, row)
      this.dialogDetailVisible = true
    },
    handleCreate() {
      this.resetTableColumnData()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createTableColumn() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          addTableColumn(this.tableColumnData).then((response) => {
            this.fetchTableColumnList()
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
      this.tableColumnData = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateTableColumn() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.tableColumnData)
          editTableColumn(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.tableColumnData.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.tableColumnData)
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
    delTableColumn(row) {
      row.status = 0
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success',
        duration: 2000
      })
      const index = this.list.indexOf(row)
      this.list.splice(index, 1)
    },
    handleDbColumn() {
      console.log('handleDbColumn')
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
