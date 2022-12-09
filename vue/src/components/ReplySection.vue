<template>
  <div>
      Reply Section
    <div v-for="reply in replies" :key="reply.replyId">
      <div>User {{ reply.usernameFrom }} : {{ reply.replyText }}</div>
    </div>
  </div>
</template>

<script>
export default {
    computed: {
        nestedReplies() {
            return this.$store.state.activeNestedReplies;
        }
    },
    methods: {
        createNestedReplies() {
            // create array of sub-replies
            const subReplies = this.replies.filter(reply => reply.replyToReplyId != 0);
            subReplies.sort((replyA, replyB) => replyA.replyId - replyB.replyId);

            // add all direct post replies to nested replies
            const nestedReplies = [];
            this.replies.forEach(currentReply => {
                if(currentReply.replyToReplyId == 0) {
                    const replyObject = {
                        reply: currentReply
                    };
                    nestedReplies.push(replyObject);
                }
            });

            // Recursively add the sub-replies to the nested replies
            


            this.$store.commit("SET_ACTIVE_NESTED_REPLIES", subReplies);
        },
        addSubReply(replyObjectToAdd, repliesArray) {
            const newRepliesArray = repliesArray.map(replyObject => {
                const newReplyObject = replyObject;

                // If the current reply object is the one being replied to by the sub-reply
                if(replyObject.reply.replyId == replyObjectToAdd.replyToReplyId) {
                    // If the current reply object has no sub-replies yet
                    if(!replyObject.subReplies) {
                        newReplyObject.subReplies = [replyObjectToAdd];
                    } else {
                        newReplyObject.subReplies.push(replyObjectToAdd);
                    }
                // If the current reply object is not the one being replied to, search deeper to see if any sub-reply objects is the one being replied to
                } else {
                    if(replyObject.subReplies) {
                        newReplyObject.subReplies = this.addSubReply(replyObjectToAdd, replyObject.subReplies);
                    }
                }

                return newReplyObject;
            });

            return newRepliesArray;
        }
    },
    props: {
        replies: Array,
    },
    created() {
        this.createNestedReplies();
    },
}
</script>

<style>

</style>