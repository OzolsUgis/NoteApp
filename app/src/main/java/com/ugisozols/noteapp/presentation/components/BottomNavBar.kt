package com.ugisozols.noteapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ugisozols.noteapp.presentation.ui.theme.MainAccent
import com.ugisozols.noteapp.presentation.ui.theme.SurfaceColor
import com.ugisozols.noteapp.presentation.ui.theme.UnselectedColor
import com.ugisozols.noteapp.presentation.ui.theme.navigationItemFontSize
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
        backgroundColor = SurfaceColor
    ) {
        items.forEach { item ->
            val selected = item.route == backstack.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = MainAccent,
                unselectedContentColor = UnselectedColor,
                onClick = {},
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.title)
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