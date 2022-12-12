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
      <h2>
        <router-link
          class="highlighted"
          :to="{ name: 'user-posts', params: { username: post.username } }"
          ><font-awesome-icon :icon="['fas', 'circle-user']" size="lg" />  {{ post.username }}</router-link
        >
      </h2>
      <h3>{{ post.datetime }}</h3>
      <div id="votes">
        <div class="up-vote" >
          <a href="#" @mouseover="isActive=true" @mouseleave="isActive=false">
            <i v-if="!isActive">
              <font-awesome-icon :icon="['far', 'circle-up']" size="lg" class="up-color" />
            </i>
            <span v-else >
              <font-awesome-icon :icon="['fas', 'circle-up']" size="lg" class="up-color" />
            </span>
          </a>
        </div>
        <div class="down-vote" >
          <a href="#" @mouseover="isActive=true" @mouseleave="isActive=false">
            <i v-if="!isActive">
              <font-awesome-icon :icon="['far', 'circle-down']" size="lg" class="down-color" />
            </i>
            <span v-else >
              <font-awesome-icon :icon="['fas', 'circle-down']" size="lg" class="down-color" />
            </span>
          </a>
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
      isActive: false
    }
  }
};
</script>

<style scoped>
#post-image {
  width: 500px;
}

div #votes {
  align-content: flex-start;
}

.up-color {
  color: rgb(255, 102, 0);
}

.down-color {
  color: rgb(76, 0, 255);
}

#post-container {
  border: groove;
  border-width: 5px;
  border-color: #76acbd;
  background-color: #c5d6db;
  margin-bottom: 10px;
}
</style>
