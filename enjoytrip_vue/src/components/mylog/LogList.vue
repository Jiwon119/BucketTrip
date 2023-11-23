<script setup>
import { ref, onMounted, watch } from "vue";
import { useRoute } from "vue-router";
import { getUserPosts } from "@/api/board";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import VSelect from "@/components/common/VSelect.vue";
import BoardListItem from "@/components/board/item/BoardListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);


const route = useRoute();
const destinationId = ref(route.query.destinationId);

const selectOption = ref([
    { text: "검색조건", value: "" },
    { text: "글번호", value: "article_no" },
    { text: "제목", value: "subject" },
    { text: "작성자아이디", value: "user_id" },
]);

const articles = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;

const param = ref({
    pgno: currentPage.value,
    spp: VITE_ARTICLE_LIST_SIZE,
    key: "",
    word: "",
    userIdSearch: ""
});

onMounted(() => {
    param.value.userIdSearch = userInfo.value.id
    getArticleList();
});

const changeKey = (val) => {
    console.log("BoarList에서 선택한 조건 : " + val);
    param.value.key = val;
};

const getArticleList = () => {
    // API 호출
    getUserPosts(
        param.value,
        ({ data }) => {
            console.log(data);
            articles.value = data.articles;
            currentPage.value = data.currentPage;
            totalPage.value = data.totalPageCount;
            console.log(articles.value);
        },
        (error) => {
            console.log(error);
        }
    );
};

const onPageChange = (val) => {
    console.log(val + "번 페이지로 이동 준비 끝!!!");
    currentPage.value = val;
    param.value.pgno = val;
    getArticleList();
};

</script>

<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10 board-list-item">
                <div class="row align-self-left mb-2">
                    <div class="col-md-5 offset-7">
                        <form class="d-flex">
                            <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control" v-model="param.word" placeholder="검색어..." />
                                <button class="btn btn-dark" type="button" @click="getArticleList">
                                    검색
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <table class="table table-hover">
                    <thead>
                        <tr class="text-center">
                            <th scope="col">글번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <template v-for="article in articles" :key="article.articleNo" :article="article">
                            <tr class="text-center">
                                <th scope="row">{{ article.articleNo }}</th>
                                <td class="text-start">
                                    <router-link :to="{ name: 'article-view', params: { articleno: article.articleNo } }"
                                        class="article-title link-dark">
                                        {{ article.subject }}
                                    </router-link>
                                </td>
                                <td>{{ article.registerTime }}</td>

                            </tr>
                        </template>
                    </tbody>
                </table>
            </div>
            <PageNavigation :current-page="currentPage" :total-page="totalPage" @pageChange="onPageChange"></PageNavigation>
        </div>
    </div>
</template>

<style scoped>
a {
    text-decoration: none;
}
</style>
