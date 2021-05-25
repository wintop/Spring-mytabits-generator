<template>
  <div class="main-wrapper">
    <div class="filter-container">
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row>
      <el-table-column label="数据库类型" align="center">
        <template slot-scope="{row}">
          {{ row.type }}
        </template>
      </el-table-column>
      <el-table-column label="数据库名称" align="center">
        <template slot-scope="{row}">
          {{ row.name }}
        </template>
      </el-table-column>
      <el-table-column label="用户名" align="center">
        <template slot-scope="{row}">
          {{ row.userName }}
        </template>
      </el-table-column>
      <el-table-column label="密码" align="center">
        <template slot-scope="{row}">
          {{ row.password }}
        </template>
      </el-table-column>
      <el-table-column label="主机名/IP" align="center">
        <template slot-scope="{row}">
          {{ row.host }}
        </template>
      </el-table-column>
      <el-table-column label="端口号" align="center">
        <template slot-scope="{row}">
          {{ row.port }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="{row}">
          {{ row.createTime | timeFilter }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="220px">
        <template slot-scope="{row}">
          <el-button size="mini" type="success" @click="handleDetail(row)">详情</el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(row)">修改</el-button>
          <el-button type="primary" size="mini" @click="getTables(row)">表信息</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataBaseData" label-position="left" label-width="90px">
        <el-form-item label="数据库类型">
          <el-select v-model="dataBaseData.type" placeholder="请选择">
            <el-option v-for="dbType in dbTypeList" :key="dbType.value" :label="dbType.name" :value="dbType.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="数据库名称">
          <el-input v-model="dataBaseData.name" placeholder="请填写数据库名称" />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="dataBaseData.userName" placeholder="请填写用户名" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="dataBaseData.password" placeholder="请填写密码" />
        </el-form-item>
        <el-form-item label="主机名或IP">
          <el-input v-model="dataBaseData.host" placeholder="请填写主机名或IP" />
        </el-form-item>
        <el-form-item label="端口号">
          <el-input v-model="dataBaseData.port" placeholder="请填写端口号" />
        </el-form-item>
        <el-form-item label="字符集">
          <el-input v-model="dataBaseData.charSet" placeholder="请填写字符集" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createDataBase():updateDataBase()">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="详情" top="6hv" :visible.sync="dialogDetailVisible">
      <el-form ref="dataForm" :model="dataBaseData" label-position="left" label-width="90px">
        <el-form-item label="数据库类型">
          <span>{{ dataBaseData.type }}</span>
        </el-form-item>
        <el-form-item label="数据库名称">
          <span>{{ dataBaseData.name }}</span>
        </el-form-item>
        <el-form-item label="用户名">
          <span>{{ dataBaseData.userName }}</span>
        </el-form-item>
        <el-form-item label="密码">
          <span>{{ dataBaseData.password }}</span>
        </el-form-item>
        <el-form-item label="主机名/IP">
          <span>{{ dataBaseData.host }}</span>
        </el-form-item>
        <el-form-item label="端口号">
          <span>{{ dataBaseData.port }}</span>
        </el-form-item>
        <el-form-item label="字符类型">
          <span>{{ dataBaseData.charSet }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ dataBaseData.createTime | timeFilter }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogDetailVisible = false">关闭</el-button>
      </div>
    </el-dialog>

    <el-dialog title="表信息" :visible.sync="dialogTableVisible">
      <template>
        <el-table
          ref="multipleTable"
          :data="tableList"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column
            type="selection"
            width="55"
          />
          <el-table-column
            label="表名"
            width="180"
          >
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>
          <el-table-column
            prop="comment"
            label="说明"
          />
          <el-table-column
            prop="dbName"
            label="所属库"
          />
        </el-table>
        <div style="margin-top: 20px">
          <el-button @click="getSelection()">同步到数据表记录中</el-button>
        </div>
      </template>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getDataBaseList, addDataBase, editDataBase, getDatabases, getDbTables, copyDbTables } from '@/api/database'
import { parseTime } from '@/utils'
import { dbTypeList, statusList, getStatusName } from '@/utils/config'
import { setCurrDatabase } from '@/utils/curr-data'

export default {
  name: 'DataBase',
  components: {
  },
  filters: {
    timeFilter(time, format) {
      return parseTime(time, format)
    },
    statusFilter(value) {
      return getStatusName(value)
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
      statusOptions: statusList,
      dbTypeList: dbTypeList,
      dataBaseData: {},
      dialogFormVisible: false,
      dialogDetailVisible: false,
      dialogTableVisible: false,
      tableList: [],
      tableSelection: [],
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '添加'
      },
      rules: {}
    }
  },
  created() {
    this.fetchDataBaseList()
  },
  methods: {
    fetchDataBaseList() {
      this.listLoading = true
      getDataBaseList(this.listQuery).then(response => {
        this.list = response.data
        this.total = response.total
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchDataBaseList()
    },
    resetDataBaseData() {
      this.dataBaseData = {
        type: 'Mysql',
        userName: 'root',
        password: '',
        host: 'localhost',
        port: '3306',
        charSet: 'utf8'
      }
    },
    handleDetail(row) {
      this.dataBaseData = Object.assign({}, row)
      this.dialogDetailVisible = true
    },
    handleCreate() {
      this.resetDataBaseData()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createDataBase() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          addDataBase(this.dataBaseData).then((response) => {
            this.fetchDataBaseList()
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
      this.dataBaseData = Object.assign({}, row)
      console.log(this.dataBaseData)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateDataBase() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.dataBaseData)
          editDataBase(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.dataBaseData.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.dataBaseData)
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
    getDatabase(row) {
      getDatabases(row).then(res => {
        console.log(res)
      })
    },
    getTables(row) {
      setCurrDatabase(this, row.name)
      getDbTables(row).then(res => {
        this.tableList = res.data
        this.dialogTableVisible = true
      })
    },
    handleSelectionChange(val) {
      this.tableSelection = val
    },
    getSelection() {
      copyDbTables(this.tableSelection).then(res => {
        this.$notify({
          title: '成功',
          message: '同步成功',
          type: 'success',
          duration: 2000
        })
      })
    }
  }
}
</script>
<style scoped>
.main-wrapper {
  margin: 8px;
}

.filter-container {
  padding: 10px 0;
  text-align: right;
}
.filter-item {
  width: 200px;
  margin-right: 10px;
}
</style>
