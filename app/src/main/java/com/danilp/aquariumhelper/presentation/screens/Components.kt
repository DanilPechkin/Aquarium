package com.danilp.aquariumhelper.presentation.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.danilp.aquariumhelper.R
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
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
fun GridItem(
    name: String,
    message: String,
    cardColors: CardColors,
    imageUri: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = cardColors,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        GlideImage(
            imageModel = imageUri.ifBlank { (R.drawable.aquairum_pic) },
            contentDescription = name,
            contentScale = ContentScale.FillWidth,
//          TODO:  placeholder = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(top = 6.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)
        ) {
            Text(text = name, style = MaterialTheme.typography.titleMedium, maxLines = 1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun ImagePicker(
    imageUri: String,
    onSelection: (Uri?) -> Unit,
    modifier: Modifier = Modifier
) {
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        onSelection(it)
    }

    GlideImage(
        imageModel = imageUri.ifBlank { (R.drawable.aquairum_pic) },
        contentDescription = stringResource(R.string.imagepicker_content_descr),
        contentScale = ContentScale.FillWidth,
        // TODO: placeholder,
        modifier = modifier
            .clickable {
                galleryLauncher.launch("image/*")
            }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    errorMessage: String? = null,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = label)
            },
            modifier = textFieldModifier,
            isError = errorMessage != null,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
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
}

@Composable
fun FromToInfoFields(
    label: String,
    valueFrom: String,
    valueTo: String,
    onValueFromChange: (String) -> Unit,
    onValueToChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Decimal,
        imeAction = ImeAction.Next
    ),
    keyboardActionsFrom: KeyboardActions = KeyboardActions(),
    keyboardActionsTo: KeyboardActions = KeyboardActions(),
    errorMessageFrom: String? = null,
    errorMessageTo: String? = null
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoFieldWithError(
                value = valueFrom,
                onValueChange = onValueFromChange,
                label = stringResource(R.string.label_from),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActionsFrom,
                errorMessage = errorMessageFrom,
                maxLines = 1,
                singleLine = true
            )
            InfoFieldWithError(
                value = valueTo,
                onValueChange = onValueToChange,
                label = stringResource(R.string.label_to),
                modifier = Modifier.weight(1f),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActionsTo,
                errorMessage = errorMessageTo,
                maxLines = 1,
                singleLine = true
            )
        }
    }
}