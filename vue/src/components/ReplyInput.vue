<template>
  <div>
      <div class="reply-input">
        <div>
          <label v-if="this.isDirectReply" for="reply-input">Reply to This Post</label>
          <div v-else >
            <h2>Reply to
              <label id="reply-to" v-if="!this.targetReply.deleted" for="reply-input">@{{this.targetReply.  usernameFrom}}</label>
            
            <label v-else for="reply-input">Reply to deleted reply</label>
            </h2>
          </div>
        </div>
        <div>
          <textarea id="reply-text" v-model="replyInput.replyText" />
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
        },
    },
    methods: {
        createReply(replyInput) {
          const reply = {};
          if(this.targetReply.replyId) {
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
        getReplyWithId() {
          console.log("get reply with id method");
          let replyWithId = {};

          replyService.getRepliesByPost(this.postId).then((response) => {

            const postReplies = response.data;
            console.log(postReplies);
            // find replies retrieved from the database similar to the newly saved one
            const similarReplies = postReplies.filter(
              postReply => {
                console.log(postReply.replyId);
                let similar = true;
                if (this.reply.postId != postReply.postId) {
                  similar = false;
                } else if (this.reply.userFrom != postReply.userFrom) {
                  similar = false;
                } else if (this.reply.replyToReplyId != postReply.replyToReplyId) {
                  similar = false;
                } else if (this.reply.replyText != postReply.replyText) {
                  similar = false;
                } else if (this.reply.mediaLink != postReply.mediaLink) {
                  similar = false;
                }
                return similar;
              }
            );
            console.log(similarReplies);
            console.log(similarReplies.length);
            // if there's only one, that's the one that was just saved
            if (similarReplies.length == 1) {
              console.log("this is activated");
              replyWithId = similarReplies[0];

            // else, find the most recently saved reply
            } else if (similarReplies.size > 1) {
              replyWithId = similarReplies.reduce(
                (accumulator, currentReply) => {
                  if(accumulator.replyId >= currentReply.replyId) {
                    return accumulator;
                  } else {
                    return currentReply;
                  }
                }
              );
            }

            console.log(replyWithId);

            this.replyInput = {
              replyText: "",
              mediaLink: ""
            }
            this.$store.commit("SAVE_REPLY", replyWithId);

            return replyWithId;
          });
        },
        saveReply() {
          replyService.addReply(this.reply)
          .then(response => {
              if (response.status === 201) {
                  this.getReplyWithId();
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
#reply-to {
  font-family: "Inter";
  text-decoration-line: underline;
  font-weight: bold;
}
.reply-input {
  font-family: "Inter";
  text-indent: 10%;
  text-align: left;
}
#reply-text {
  font-family: "Inter";
  display: inline-block;
  width: 50%;
  padding:50px 10px;
}
</style>