<template>
    <form class="new-book-form" v-on:submit.prevent="saveBook">
        <input class="title-input" type="text" placeholder="Title"/>
        <input class="media-input" type="text" placeholder="Media Link"/>
        <input class="text-input" type="text" placeholder="Text"/>
        <button>Add Post</button>
    </form>
</template>

<script>
export default {

}
</script>

<style>

</style>