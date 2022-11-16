<template>
    <b-card no-body>
        <b-card-header class="border-0">
            <h3 class="mb-0">공지사항</h3>

         
        <a href="#!" class="btn btn-outline-success float-righ" style="float: right;">글쓰기</a>
        
        </b-card-header>

        <el-table class="table-responsive table"  header-row-class-name="thead-light" :data="boards" @row-click="rowClick">
            
            <el-table-column label="articleno" min-width="100px" prop="articleno" >
            </el-table-column>

            <el-table-column label="subject" min-width="400px" prop="subject" >
                
            </el-table-column>
    

            <el-table-column label="regtime" min-width="100px" prop="regtime">
            </el-table-column>
            
        </el-table>
        
        <b-card-footer class="py-4 d-flex justify-content-end">
            <base-pagination v-model="currentPage" :per-page="10" :total="50"></base-pagination>
        </b-card-footer>
    </b-card>
</template>
<script>

import { mapGetters } from "vuex";
  import { Table, TableColumn} from 'element-ui'
  export default {
    name: 'light-table',
    components: {
      [Table.name]: Table,
      [TableColumn.name]: TableColumn
    },

    name: "BoardList",
    computed: {
    ...mapGetters(["boards"])
    },
    created() {
    this.$store.dispatch("getBoards");
    },
    methods: {
        rowClick(row) {
          console.log("hi");
          this.$router.push(`/tables/view?articleno=${row.articleno}`);
        },
        
    },
    data() {
      return {
        boards,
        currentPage: 1
      };
    }
  }
</script>
