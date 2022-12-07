<template>
    <div>
        <div>{{forums}}</div>
        <form class="create-post" v-on:submit.prevent="savePost">
            
            <select v-model="selectedForum">
                <option v-for="forum in forums" :key = "forum.name" :value="forum">{{forum.name}}</option>
            </select>
            <input class="title-input" type="text" placeholder="Title"/>
            <!-- <input class="media-input" type="text" placeholder="Media-Link" v-model="post.mediaLink"/>
            <input class="text-input" type="text" placeholder="Text" v-model="post.text"/> 
            <button>Add Post</button> -->
        </form>
    </div>
</template>

<script>
import forumService from "../services/ForumService.js";

export default {
    name: "create-post",
    data() {
        return {
            selectedForum: {},
            post: {
                userId: this.$store.state.user.id,
                username: this.$store.state.user.username,
                forumId: this.selectedForum.forumId,
                forumName: this.selectedForum.name,
                title: "",
                text: "",
                mediaLink: ""
            }
        }
    },
    methods: {
        getForums() {
            forumService.getForums().then(response => {
                this.$store.commit("SET_FORUMS", response.data);
            });
        },
        savePost() {
            this.$store.commit("SAVE_POST", this.post);
            this.post = {
                userId: this.$store.state.user.id,
                username: this.$store.state.user.username,
                forumId: null,
                forumName: "",
                title: "",
                text: "",
                mediaLink: ""
            }
            this.$router.push("/");
        }
    },
    computed: {
        forums() {
            return this.$store.state.forums;
        }
    },
    created() {
        this.getForums();
    }

}
</script>

<style>

</style>