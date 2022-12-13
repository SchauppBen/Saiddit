<template>
  <div>
    <span class="reply">{{reply.usernameFrom}}: {{ reply.replyText }}</span>
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

  }
}
</script>

<style>
  .sub-replies {
    padding-left: 25px;
  }
</style>