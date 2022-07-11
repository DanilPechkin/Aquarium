package com.danilp.aquariumhelper.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.NavController
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.NavGraphs
import com.danilp.aquariumhelper.presentation.screens.appCurrentDestinationAsState
import com.danilp.aquariumhelper.presentation.screens.aquarium.list.AquariumListEvent
import com.danilp.aquariumhelper.presentation.screens.destinations.MainAquariumScreenDestination
import com.danilp.aquariumhelper.presentation.screens.startAppDestination
import com.danilp.aquariumhelper.presentation.ui.theme.AquariumHelperTheme
import com.ramcosta.composedestinations.annotation.Destination

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    isSearchFieldVisible: Boolean,
    hideSearchField: () -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = isSearchFieldVisible) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { hideSearchField() }),
            placeholder = {
                Text(text = stringResource(R.string.search_placeholder))
            },
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .focusRequester(focusRequester)
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Composable
fun InfoFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    errorMessage: String? = null,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        isError = errorMessage != null,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        singleLine = singleLine
    )
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error
        )
    }
}
