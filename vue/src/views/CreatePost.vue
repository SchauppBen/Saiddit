<template>
    <div>
        <form class="create-post" v-on:submit.prevent>
            <select v-model="selectedForum">
                <option v-for="forum in forums" :key = "forum.name" :value="forum">{{forum.name}}</option>
            </select>

            <input class="title-input" type="text" placeholder="Title" v-model="post.title" />
            <input class="media-input" type="text" placeholder="Media-Link" v-model="post.mediaLink"/>
            <input class="text-input" type="text" placeholder="Text" v-model="post.text"/> 
            <button type="submit" v-on:click="savePost()">Add Post</button>
        </form>
    </div>
</template>

<script>
import forumService from "../services/ForumService.js";
import postService from "../services/PostService.js";

export default {
    name: "create-post",
    data() {
        return {
            selectedForum: {
                forumId: 0,
                name: "",
                description: "",
                dateCreated: ""
            },
            post: {
                userId: this.$store.state.user.id,
                username: this.$store.state.user.username,
                forumId: 0,
                forumName: "",
                title: "",
                text: "",
                mediaLink: ""
            }
        };
    },
    watch: {
        selectedForum(newSelectedForum) {
            this.post.forumId = newSelectedForum.forumId;
            this.post.forumName = newSelectedForum.name;
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
            postService.addPost(this.post)
                .then(response => {
                    if (response.status === 201) {
                        this.post = {
                            userId: this.$store.state.user.id,
                            username: this.$store.state.user.username,
                            forumId: 0,
                            forumName: "",
                            title: "",
                            text: "",
                            mediaLink: ""
                        }
                        this.$router.push('/');
                    }
                })
                .catch(error => {
                    this.handleErrorResponse(error, "adding");
                });
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