<template>
  <div>
    <span class="reply">{{ reply.replyText }}</span>
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
export default {
  name: "recursive-reply",
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

</style>