<template>
  <div>
      <div class = "reply-input">
        <div>
          <label v-if="this.isDirectReply" for="reply-input">Reply to This Post</label>
          <div v-else >
            <label v-if="!this.targetReply.deleted" for="reply-input">Reply to {{this.targetReply.  usernameFrom}}</label>
            <label v-else for="reply-input">Reply to deleted reply</label>
          </div>
        </div>
        <div>
          <input type="text" id="reply-input" name="replyInput" v-model="replyInput.replyText"/>
        </div>
      </div>
      <div>
        <button :disabled="this.reply.replyText.length == 0" v-on:click="saveReply()">reply</button>
      </div>
  </div>
</template>
<script>
import replyService from "../services/ReplyService.js";
export default {
    name: 'reply-input',
    data() {
      return {
        replyInput: {
          replyText: "",
          mediaLink: ""
        },
      }
    },
    props: {
      postId: Number,
      isDirectReply: Boolean,
      targetReply: {
        type: Object,
        default: () => ({})
      }
    },
    computed: {
        reply() {
            return this.createReply(this.replyInput);
        }
    },
    methods: {
        createReply(replyInput) {
            const reply = {};
            if(this.targetReply.replyId) {
              console.log("this statement is reached");
              reply.replyToReplyId = this.targetReply.replyId;
            } else {
              reply.replyToReplyId = 0;
            }
            reply.postId = this.postId;
            reply.userFrom = this.$store.state.user.id;
            reply.usernameFrom = this.$store.state.user.username;
            reply.replyText = replyInput.replyText;
            reply.mediaLink = replyInput.mediaLink;
            return reply;
        },
        saveReply() {
          this.$store.commit("SAVE_REPLY", this.reply);
          replyService.addReply(this.reply)
          .then(response => {
              if (response.status === 201) {
                  this.replyInput = {
                    replyText: "",
                    mediaLink: ""
                  }
              }
          })
          .catch(error => {
              this.handleErrorResponse(error, "adding");
          });
        }
    }
}
</script>
<style>
    .reply-input {
    text-indent: 10%;
    text-align: left;
    }
    #reply-input {
    display: inline-block;
    width: 50%;
    padding:50px 10px;
    }
</style>