package com.danilp.aquariumhelper.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.ui.theme.AquariumHelperTheme

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItem(
    name: String,
    message: String,
    cardColors: CardColors,
    //TODO: image
    modifier: Modifier = Modifier
) {
    Card(
        colors = cardColors,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.aquairum_pic),
            contentDescription = name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(top = 6.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)
        ) {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message, style = MaterialTheme.typography.labelMedium)
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

@Composable
fun FromToInfoFields(
    label: String,
    valueFrom: String,
    valueTo: String,
    onValueFromChange: (String) -> Unit,
    onValueToChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
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
                errorMessage = errorMessageTo,
                maxLines = 1,
                singleLine = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FromToInfoFieldsPreview() {
    AquariumHelperTheme {
        FromToInfoFields(
            label = "Temperature",
            valueFrom = "15",
            valueTo = "20",
            onValueFromChange = {},
            onValueToChange = {}
        )
    }
}