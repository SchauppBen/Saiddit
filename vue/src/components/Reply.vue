<template>
  <div>
    <a
      id="show-form-button"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
    >Show Form</a>

    <form v-on:submit.prevent="addNewReview" v-if="showForm === true">
      <div class="form-element">
        <label for="reviewer">Name:</label>
        <input id="reviewer" type="text" v-model="newReview.reviewer" />
      </div>
      <div class="form-element">
        <label for="title">Title:</label>
        <input id="title" type="text" v-model="newReview.title" />
      </div>
      <div class="form-element">
        <label for="rating">Rating:</label>
        <select id="rating" v-model.number="newReview.rating">
          <option value="1">1 Star</option>
          <option value="2">2 Stars</option>
          <option value="3">3 Stars</option>
          <option value="4">4 Stars</option>
          <option value="5">5 Stars</option>
        </select>
      </div>
      <div class="form-element">
        <label for="review">Review:</label>
        <textarea id="review" v-model="newReview.review"></textarea>
      </div>
      <input type="submit" value="Save" />
      <input type="button" value="Cancel" v-on:click="resetForm" />
    </form>
     <!-- If current reply is not deleted, show text & delete reply button -->
    <div v-if="!this.reply.deleted">
      <span class="reply" >{{reply.usernameFrom}}: {{ reply.replyText }}</span>
      <!-- Delete reply button -->
      <button v-if="this.reply.userFrom == this.$store.state.user.id" v-on:click="deleteReply()">   
        delete reply
      </button>
    </div>
    <span class="reply" v-else>This reply has been deleted</span>

    <!-- If current reply is not deleted, show replier's username -->
    <reply-input class="reply-input" :postId="this.reply.postId" :isDirectReply="false" :targetReply="this.reply" />

    <ul class="sub-replies" v-if="subReplies.length">
      <div v-for="(principalReplyObject, index) in subReplies" :key="index">
        <recursive-reply
          v-bind="{
            reply: principalReplyObject.reply,
            subReplies: principalReplyObject.subReplies,
          }"
        />
      </div>
    </ul>
  </div>
</template>

<script>
import replyService from "../services/ReplyService.js";

import replyInput from "./ReplyInput.vue";

export default {
  name: "recursive-reply",
  components: {
    replyInput
  },
  props: {
    reply: {
      type: Object,
      required: true
    },
    subReplies: {
      type: Array,
      default: () => []
    }

  },
  methods: {
    deleteReply() {
      this.$store.commit("DELETE_REPLY", this.reply);
      replyService.deleteReply(this.reply.replyId)
      .catch((error) => {
        this.handleErrorResponse(error, "deleting");
      });
    }
  }
}
</script>

<style>
  .sub-replies {
    padding-left: 25px;
  }

  .reply {
    font-family: Georgia, "Times New Roman", Times, serif;
    color: #2e6c99;
  }
</style>