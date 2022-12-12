<template id="font-awesome-icon">
  <div id="post-container" class="posts">
    <div id="postBox">
      <h4>
        <router-link
          class="highlighted"
          :to="{ name: 'forum-view', params: { forumName: post.forumName } }"
          >[{{ post.forumName }}]</router-link
        >
      </h4>
      <router-link
        :to="{
          name: 'post-details',
          params: { forumName: post.forumName, postId: post.postId },
        }"
      >
        <h1>{{ post.title }}</h1>
        <img id="post-image" :src="post.mediaLink" v-show="post.mediaLink" />
        <h3>{{ post.text }}</h3></router-link
      >
      <div class="inline">
        <h2 id="user">
          <router-link
            class="highlighted"
            :to="{ name: 'user-posts', params: { username: post.username } }"
            ><font-awesome-icon :icon="['fas', 'circle-user']" size="lg" />  {{ post.username }}</router-link
          >
        </h2>
        <h3>{{ post.datetime }}</h3>
        <div class="votes">
          <div class="up-vote" >
            <button @click.prevent @mouseover="isUpActive=true" @mouseleave="isUpActive=false">
              <i v-if="!isUpActive">
                <font-awesome-icon :icon="['far', 'circle-up']" size="lg" class="up-color" />
              </i>
              <span v-else >
                <font-awesome-icon :icon="['fas', 'circle-up']" size="lg" class="up-color" />
              </span>
            </button>
          </div>
          <div class="down-vote" >
            <button @click.prevent @mouseover="isDownActive=true" @mouseleave="isDownActive=false">
              <i v-if="!isDownActive">
                <font-awesome-icon :icon="['far', 'circle-down']" size="lg" class="down-color" />
              </i>
              <span v-else >
                <font-awesome-icon :icon="['fas', 'circle-down']" size="lg" class="down-color" />
              </span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Votes',
  props: {
    post: Object,
  },
  data() {
    return {
      myIcon: {
        farIcon: ['far', 'circle-up'],
        fasIcon: ['fas', 'circle-up'],
      },      
      isUpActive: false,
      isDownActive: false
    }
  }
};
</script>

<style scoped>
button {
    background-color: transparent;
    background-repeat: no-repeat;
    border: none;
    cursor: pointer;
    overflow: hidden;
    outline: none;
    font-size: 17px;
}

#post-image {
  width: 500px;
}

.inline, .votes {
  display: flex;
  flex-direction: row;
  justify-content: center;
}



#post-container {
  border: groove;
  border-width: 5px;
  border-color: #76acbd;
  background-color: #c5d6db;
  margin-bottom: 10px;
}
</style>
