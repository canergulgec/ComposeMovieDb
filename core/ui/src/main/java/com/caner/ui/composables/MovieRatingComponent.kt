package com.caner.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caner.ui.theme.ComposeMovieDbTheme
import java.util.Locale

@Composable
fun MovieRatingChip(rating: Double, voteCount: Int? = null) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = when {
            rating >= 8.0 -> Color(0xFF4CAF50)
            rating >= 6.0 -> Color(0xFFFF9800)
            else -> Color(0xFFF44336)
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = String.format(Locale.getDefault(), "%.1f", rating),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            voteCount?.let {
                Text(
                    text = " (${formatVoteCount(it)})",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

private fun formatVoteCount(count: Int): String {
    return when {
        count >= 1000000 -> "${count / 1000000}M"
        count >= 1000 -> "${count / 1000}K"
        else -> count.toString()
    }
}

@Preview
@Composable
fun MovieRatingChipGoodPreview() {
    ComposeMovieDbTheme {
        MovieRatingChip(rating = 8.0, voteCount = 1000)
    }
}

@Preview
@Composable
fun MovieRatingChipMediumPreview() {
    ComposeMovieDbTheme {
        MovieRatingChip(rating = 6.0, voteCount = 10000)
    }
}

@Preview
@Composable
fun MovieRatingChipBadPreview() {
    ComposeMovieDbTheme {
        MovieRatingChip(rating = 4.0, voteCount = 100)
    }
}