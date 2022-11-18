<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <h3 class="mb-0">공지사항</h3>

      <a class="btn btn-outline-success float-right" @click="moveWrite()">글쓰기</a>
    </b-card-header>

    <el-table
      class="table-responsive table"
      header-row-class-name="thead-light"
      :data="boards"
      @row-click="rowClick"
    >
      <el-table-column label="no" min-width="20%" prop="articleno">
      </el-table-column>

      <el-table-column label="subject" min-width="60%" prop="subject">
      </el-table-column>

      <el-table-column label="regtime" min-width="20%" prop="regtime">
      </el-table-column>
    </el-table>

    <b-form-group class="mt-2 mx-auto">
      <b-input-group class="border border-1-dark">
        <div class="input-group-prepend">
          <span class="input-group-text"><i class="fas fa-search"></i></span>
        </div>
        <b-form-input placeholder="Search" type="text"></b-form-input>
      </b-input-group>
    </b-form-group>

    <b-card-footer class="py-4 d-flex justify-content-end">
      <base-pagination
        v-model="currentPage"
        :per-page="10"
        :total="boards.length"
      ></base-pagination>
    </b-card-footer>
  </b-card>
</template>
<script>
import { mapGetters } from "vuex";
import { Table, TableColumn } from "element-ui";
export default {
  name: "NoticeTable",
  components: {
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  },
  computed: {
    ...mapGetters("board", ["boards"]),
  },
  created() {
    this.$store.dispatch("board/getBoards", {});
  },
  mounted() {
    this.currentPage = 1;
  },
  methods: {
    rowClick(row) {
      console.log("hi");
      this.$router.push(`/tables/view?articleno=${row.articleno}`);
    },
    moveWrite(){
      this.$router.push("/tables/write");
    }
  },
  data() {
    return {
      currentPage: 1,
    };
  },
};
</script>

