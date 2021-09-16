package com.ugisozols.noteapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.NavBarItem


@Composable
fun BottomNavBar(
    items : List<NavBarItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick : (NavBarItem) -> Unit
) {
    val backstack = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = SurfaceColor,

    ) {
        items.forEach { item ->
            val selected = item.route == backstack.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = MainAccent,
                unselectedContentColor = UnselectedColor,
                onClick = {
                          onItemClick(item)
                },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(navigationIconSize)
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            fontSize = navigationItemFontSize
                        )
                    }
                }
            )
        }
    }
}