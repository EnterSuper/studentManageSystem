<template>
  <!-- 方格布局-->
  <div>
    <el-row>
      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc; text-align: center;">
          <img style="width: 100%;" src="@/assets/logo.png" alt="">
          <div style="text-align: center;">nana fighting!</div>
        </div>
      </el-col>

      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc; text-align: center;">
          <img style="width: 100%;" src="@/assets/logo.png" alt="">
          <div style="text-align: center;">nana fighting!</div>
        </div>
      </el-col>

      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc; text-align: center;">
          <img style="width: 100%;" src="@/assets/logo.png" alt="">
          <div style="text-align: center;">nana fighting!</div>
        </div>
      </el-col>

      <el-col :span="6">
        <div style="padding: 10px; border: 1px solid #ccc; text-align: center;">
          <img style="width: 100%;" src="@/assets/logo.png" alt="">
          <div style="text-align: center;">nana fighting!</div>
        </div>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="24">
        <button>按钮</button>
        <el-button>Element独特按钮</el-button>
        <el-button type="primary">主要的按钮</el-button>
        <el-button type="danger">危险的按钮</el-button>
        <el-button type="warning">警告的按钮</el-button>
        <el-button type="info">信息的按钮</el-button>
        <el-button type="success">成功的按钮</el-button>

        <el-button type="danger" plain>危险的按钮</el-button>
<!--        朴素的按钮-->
        <el-button type="success" round>成功的按钮</el-button>
        <el-button circle icon="el-icon-edit" type="primary"></el-button>
        <el-button circle icon="el-icon-delete" type="danger"></el-button>
      </el-col>
    </el-row>
    <el-row style="margin-top: 20px">
      <el-col>
        <el-input style="width: 200px" v-model="value" placeholder="请输入内容"></el-input>
        <el-input show-password style="width: 200px" v-model="password" placeholder="请输入密码"></el-input>
        <el-input style="width: 200px" v-model="value1" placeholder="请输入密码" prefix-icon="el-icon-search"></el-input>
        <el-input clearable style="width: 200px" v-model="value2" placeholder="请输入密码" suffix-icon="el-icon-user"></el-input>
      </el-col>
    </el-row>
    <el-row style="margin: 20px 0">
      <el-autocomplete style="width: 300px" placeholder="请输入内容，我来帮你猜一猜" :fetch-suggestions="querySearch" :trigger-on-focus="false" v-model="value3"></el-autocomplete>
    </el-row>

<el-row>
  <el-select v-model="sel" multiple>
   <el-option v-for="item in fruits" :key="item.id" :value="item.name"></el-option>
  </el-select>
</el-row>

    <el-row>
      <el-select v-model="sel2" @change="changeSelectUser" filterable>
        <el-option v-for="item in users" :key="item.card" :label="item.label" :value="item.card"></el-option>
      </el-select>
    </el-row>

    <el-row style="margin: 20px 0">
      <el-table :data="tableDate" border :header-cell-style="{background:'aliceblue',fontSize:'20px'}">
        <el-table-column label="序号" prop="id" align="center"></el-table-column>
        <el-table-column label="名称" prop="name" align="center"></el-table-column>
        <el-table-column label="年龄" prop="age" align="center"></el-table-column>
        <el-table-column label="地址" prop="address" align="center"></el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
            <el-button type="primary" @click="edit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>


  </div>
</template>

<script>
export default{
  name: 'Element',
  data()
  {
    return{
      tableDate:[
        {name:'李雅娜',address:'湖南省',id:1,age:19},
        {name:'李雅娜',address:'湖南省',id:1,age:19},
        {name:'李雅娜',address:'湖南省',id:1,age:19},
        {name:'李雅娜',address:'湖南省',id:1,age:19},
        {name:'李雅娜',address:'湖南省',id:1,age:19}
      ],
      value:'',
      password:'',
      value1:'',
      value2:'',
      value3:'',
      coffees:[{value:'星巴克咖啡'},{value:'瑞幸咖啡'},{value:'库迪咖啡'}],
      sel:'',
      sel2:'',
      radio:'',
      date:'',
      datetime:'',
      daterange:'',
      fruits:[
        {name:'香蕉', id:1},
        {name:'榴莲', id:2},
        {name:'苹果', id:3},
        {name:'火龙果', id:4},
      ],
      users:[
        {label:'娜娜',card:1},
        {label:'肖肖',card:12},
        {label:'靖淇',card:123},
        {label:'彭宝贝',card:1235}
      ],
      checklist:[]
      // 显示name，绑定card
    }
  },
  methods:{
    edit(row)
    {
      //this.$message.success(row.name)弹窗
      this.$confirm('这是个什么玩意儿', '提示', {
        type: 'warning' // 指定对话框类型为警告
      }).then(() => {
        this.$message.success('OK，我点击了确认'); // 点击确认后弹出成功消息
      }).catch(() => {
        this.$message.success('OK，我点击了取消'); // 点击取消后弹出成功消息
      });

    },
    querySearch(query,cb) {
      // console.log(query)
      let result= query ? this.coffees.filter(v => v.value.includes(query)) : this.coffees
      // 通过前端筛选数组
      console.log(result)
      cb(result);
    },
    changeSelect() {
      console.log(this.sel)
    },
    changeSelectUser() {
      console.log(this.sel2)
    },
    selectRadio()
    {
      alert(this.radio)
    },
    selectCheckBox()
    {
      console.log(this.checklist)
    },
    changeDate()
    {
      console.log(this.date)
    },
    changeDatetime()
    {
      console.log(this.datetime)
    },
    Changedaterange()
    {
      console.log(this.daterange)
    }
  }
}
</script>