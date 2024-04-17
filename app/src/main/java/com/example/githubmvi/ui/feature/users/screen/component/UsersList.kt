package com.example.githubmvi.ui.feature.users.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubmvi.data.model.response.User
import com.example.githubmvi.data.model.response.buildUserPreview

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    users: List<User>,
    onItemClick: (User) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            item {
                UsersListHeader()
            }
            items(users) { user ->
                UsersListItem(user = user, onItemClick = onItemClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersListPreview() {
    val users = List(3) { buildUserPreview() }
    UsersList(users = users) {}
}