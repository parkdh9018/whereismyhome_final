
<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
